public class ByteUtils {

    public static boolean[] bytesToBits(byte[] bytes) {
        return bytesToBits(bytes, bytes.length * 8, 0);
    }

    public static boolean[] bytesToBits(byte[] bytes, int lengthOfBits, int offset) {
        boolean[] bits = new boolean[lengthOfBits];
        for (int i = 0; i < lengthOfBits; i++) {
            int byteIndex = (i + offset) / 8;
            int bitIndex = 7 - ((i + offset) % 8);
            bits[i] = ((bytes[byteIndex] >> bitIndex) & 0x01) == 1;
        }
        return bits;
    }

    public static byte[] bitsToBytes(boolean[] bits) {
        byte[] bytes = new byte[(bits.length + 7) / 8];
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                bytes[i / 8] |= (byte) (1 << (7 - (i % 8)));
            }
        }
        return bytes;
    }

    public static boolean[] leftShift(boolean[] arr, int shifts) {
        boolean[] shifted = new boolean[arr.length];

        for (int i = shifts; i < arr.length; i++) {
            shifted[i - shifts] = arr[i];
        }

        for (int i = 0; i < shifts; i++) {
            shifted[arr.length - shifts + i] = arr[i];
        }

        return shifted;
    }
}
