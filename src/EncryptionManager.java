public class EncryptionManager {
    private DES des = new DES(); // Use your provided class

    // Encrypt the password using your Vigen�re Cipher implementation
    public String encrypt(String plainTextPassword) {
        return des.Encryption(plainTextPassword); // Call your encrypt method
    }



}
