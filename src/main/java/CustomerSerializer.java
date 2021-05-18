import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomerSerializer implements Serializer<Customer> {
    @Override
    public byte[] serialize(String topic, Customer customer) {
        try {

            //byte[] serializedId = customer.getCustomerId();

        } catch (Exception e) {
            throw new SerializationException("error when serializing Customer to byte array" + e);
        }
        return new byte[0];
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}
