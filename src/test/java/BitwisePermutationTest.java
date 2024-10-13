import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitwisePermutationTest {

    // Initial Permutation table used in DES
    private static final int[] IP = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    @Test
    public void testPermutation() {
        BitwisePermutation bp = new BitwisePermutation();

        // Example input data (64-bit, 8 bytes)
        byte[] inputData = new byte[] {
                (byte) 0x13, (byte) 0x34, (byte) 0x57, (byte) 0x79,
                (byte) 0x9B, (byte) 0xBC, (byte) 0xDF, (byte) 0xF1
        };


        // Expected result after applying initial permutation
        byte[] expectedData = new byte[] {
                (byte) 0xF0, (byte) 0xCC, (byte) 0xAA, (byte) 0xF5,
                (byte) 0x56, (byte) 0x67, (byte) 0x8F
        };

        //F0 CC AA F5 56 67 8F

        // Apply the permutation
        byte[] permutedData = bp.applyPermutation(inputData, IP);

        // Verify the result
        assertArrayEquals(expectedData, permutedData, "The permuted data is incorrect.");
    }
}
