@FunctionalInterface
public interface Encryptor {
    byte[] encrypt(byte[] data, byte[] key);
}
