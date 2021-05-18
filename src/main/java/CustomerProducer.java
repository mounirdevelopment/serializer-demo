import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

public class CustomerProducer {

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","127.0.0.1:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","CustomerSerializer");

        Producer producer = new KafkaProducer<String, Customer>(kafkaProps);

        Date now = new Date();

        int i = 0;
        while (i < 10000) {

            Customer customer = new Customer(i,
                    "first_name_"+i,
                    "last_name_"+i,
                    i%20);

            ProducerRecord<String, Customer> record = new ProducerRecord("customers",
                    customer.getCustomerId()+"",
                    customer);

            try {

                producer.send(record);

            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        producer.flush();
    }
}
