/**
 * @Author: <a href="mailto:bakrahmed440@gmail.com"> Abo 7iat </a>
 */

public class DESProcessor implements Encryptor, Decryptor {
    private static final int[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };


    private static final int[] FP = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    private final Permutation permutation;
    private final KeyScheduler keyScheduler;
    private final FiestelFunction feistelFunction;

    public DESProcessor() {
        permutation = new BitwisePermutation();
        keyScheduler = new DESKeyScheduler();
        feistelFunction = new FiestelFunctionImpl();
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        return process(data, key, true);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        return process(data, key, false);
    }

    private byte[] process(byte[] data, byte[] key, boolean toBeDecrypted) {
        // Perform initial permutation
        byte[] permutedData = permutation.applyPermutation(data, IP);

        // Split into two parts
        byte[] leftHalf = new byte[4];
        byte[] rightHalf = new byte[4];
        System.arraycopy(permutedData, 0, leftHalf, 0, 4);
        System.arraycopy(permutedData, 4, rightHalf, 0, 4);

        // Generate sub keys
        byte[][] subKeys = keyScheduler.generateSubKeys(key);
        if (toBeDecrypted) {
            subKeys = reverseSubKeys(subKeys);
        }

        for (int round = 0; round < 16; round++) {
            byte[] temp = rightHalf.clone();
            byte[] fOutput = feistelFunction.apply(rightHalf, subKeys[round]);

            // XOR with left
            byte[] xorResult = new byte[leftHalf.length];
            for (int i = 0; i < leftHalf.length; i++) {
                xorResult[i] = (byte) (leftHalf[i] ^ fOutput[i]);
            }

            leftHalf = temp;
            rightHalf = xorResult;
        }

        // combine right and left
        byte[] combined = new byte[8];
        System.arraycopy(rightHalf, 0, combined, 0, 4);
        System.arraycopy(leftHalf, 0, combined, 4, 4);

        // Perform final permutation and return result
        return permutation.applyPermutation(combined, FP);
    }

    private byte[][] reverseSubKeys(byte[][] subKeys) {
        byte[][] result = new byte[subKeys.length][];
        for (int i = 0; i < subKeys.length; i++) {
            result[i] = subKeys[subKeys.length - 1 - i];
        }
        return result;
    }
}
