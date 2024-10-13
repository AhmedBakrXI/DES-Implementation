@FunctionalInterface
public interface KeyScheduler {
    /**
     * Generates sub keys for the given key
     * @param key the key to generate sub keys and is provided by the user
     * @return array of sub keys for the given key
     */
    byte[][] generateSubKeys(byte[] key);
}
