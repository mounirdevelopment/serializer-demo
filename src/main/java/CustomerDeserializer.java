import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class CustomerDeserializer implements Deserializer<Customer> {

    @Override
    public Customer deserialize(String topic, byte[] data) {
        long customerId;
        String firstName;
        String lastName;
        int firstNameSize;
        int lastNameSize;
        int age;

        try {

            if (data == null)
                return null;

            if (data.length < 12)
                throw new SerializationException("size shorten than expected");

            ByteBuffer byteBuffer = ByteBuffer.wrap(data);

            customerId = byteBuffer.getLong();
            firstNameSize = byteBuffer.getInt();
            byte[] firstNameBytes = new byte[firstNameSize];
            byteBuffer.get(firstNameBytes);
            firstName = new String(firstNameBytes, "UTF-8");
            lastNameSize = byteBuffer.getInt();
            byte[] lastNameBytes = new byte[lastNameSize];
            byteBuffer.get(lastNameBytes);
            lastName = new String(lastNameBytes, "UTF-8");
            age = byteBuffer.getInt();

            return new Customer(customerId,
                    firstName,
                    lastName,
                    age);



        } catch (Exception e) {
            throw new SerializationException("error when deserializing customer " + e);
        }
    }
}
