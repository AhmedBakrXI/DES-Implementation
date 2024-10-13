@FunctionalInterface
public interface Permutation {
    /**
     * Applies a bitwise permutation on a given data array based on the provided permutation table.
     *
     * @param data             The input data (in bytes) to be permuted.
     * @param permutationTable The permutation table defining how to reorder the bits.
     * @return The permuted data.
     */
    byte[] applyPermutation(byte[] data, int[] permutationTable);
}
