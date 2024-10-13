import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DESTest {
    @Test
    public void testDES() {
        byte[] data = new byte[] {
                0x12, 0x34, 0x56, 0x78,
                (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF
        };
        byte[] key = new byte[] {
                0x13, 0x57, (byte)0x9B, (byte)0xDF,
                0x24, 0x68, (byte)0xAC, (byte)0xE0
        };

        DESProcessor processor = new DESProcessor();
        byte[] encryptedData = processor.encrypt(data, key);
        byte[] decryptedData = processor.decrypt(encryptedData, key);
        assertArrayEquals(data, decryptedData);
    }
}
