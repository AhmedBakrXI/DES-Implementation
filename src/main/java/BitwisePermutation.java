public class BitwisePermutation implements Permutation {
    @Override
    public byte[] applyPermutation(byte[] data, int[] permutationTable) {
        int bitLength = permutationTable.length;
        byte[] permuted = new byte[(bitLength + 7) / 8];

        for (int i = 0; i < permutationTable.length; i++) {
            int position = permutationTable[i] - 1;
            int bytePosition = position / 8;
            int bitPosition = 7 - position % 8;
            // Get the bit value of the position
            int bitValue = (data[bytePosition] >> bitPosition) & 0x01;
            // Set the bit value at the corresponding position in the permuted data
            int permutedBytePosition = i / 8;
            int permutedBitPosition = 7 - i % 8;
            permuted[permutedBytePosition] |= (byte) (bitValue << permutedBitPosition);
        }

        return permuted;
    }
}
