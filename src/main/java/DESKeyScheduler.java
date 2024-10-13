public class DESKeyScheduler implements KeyScheduler {
    private static final int ROUNDS = 16;
    private static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    private static final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    private static final int[] SHIFTS = {
            1, 1, 2, 2, 2, 2, 2, 2,
            1, 2, 2, 2, 2, 2, 2, 1
    };

    @Override
    public byte[][] generateSubKeys(byte[] key) {
        byte[][] keys = new byte[ROUNDS][6];

        Permutation bitwisePermutation = new BitwisePermutation();
        byte[] pc1Key = bitwisePermutation.applyPermutation(key, PC1);

        // Split into C & D
        boolean[] C = ByteUtils.bytesToBits(pc1Key, 28, 0);
        boolean[] D = ByteUtils.bytesToBits(pc1Key, 28, 28);

        for (int i = 0; i < ROUNDS; i++) {
            C = ByteUtils.leftShift(C, SHIFTS[i]);
            D = ByteUtils.leftShift(D, SHIFTS[i]);

            // Combine C & D together
            boolean[] combination = new boolean[56];
            for (int j = 0; j < 28; j++) {
                combination[j] = C[j];
            }
            for (int j = 28; j < 56; j++) {
                combination[j] = D[j - 28];
            }

            // Apply PC2 to generate sub key
            byte[] pc2Key = bitwisePermutation.applyPermutation(ByteUtils.bitsToBytes(combination), PC2);
            keys[i] = pc2Key;
        }

        return keys;
    }
}
