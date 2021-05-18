import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerSerializer implements Serializer<Customer> {
    @Override
    public byte[] serialize(String topic, Customer customer) {
        try {

            byte[] serializedFirstName;
            int firstNameSize;
            byte[] serializedLastName;
            int lastNameSize;
            if (customer == null)
                return null;

            if (customer.getFirstName() != null) {
                serializedFirstName = customer.getFirstName().getBytes("UTF-8");
                firstNameSize = serializedFirstName.length;
            } else {
                serializedFirstName = new byte[0];
                firstNameSize = 0;
            }

            if (customer.getLastName() != null) {
                serializedLastName = customer.getLastName().getBytes("UTF-8");
                lastNameSize = serializedLastName.length;
            } else {
                serializedLastName = new byte[0];
                lastNameSize = 0;
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(8+firstNameSize+lastNameSize+4);
            byteBuffer.putLong(customer.getCustomerId());
            byteBuffer.put(serializedFirstName);
            byteBuffer.put(serializedLastName);
            byteBuffer.putInt(customer.getAge());

            return byteBuffer.array();

        } catch (Exception e) {
            throw new SerializationException("error when serializing Customer to byte array" + e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}
