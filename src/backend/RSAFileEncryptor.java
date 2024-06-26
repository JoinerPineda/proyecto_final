package backend;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAFileEncryptor {

    private static final String ALGORITHM = "RSA";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSAFileEncryptor () {
        try {
            // Generar un par de llaves RSA
            KeyPair keyPair = generateKeyPair();
            // Guardar las llaves en archivos de texto
            saveKeyToFile("publicKey.txt", keyPair.getPublic().getEncoded());
            saveKeyToFile("privateKey.txt", keyPair.getPrivate().getEncoded());

            publicKey = loadPublicKeyFromFile("publicKey.txt");
            privateKey = loadPrivateKeyFromFile("privateKey.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean encrypt (File inputFile) {
        try {
            // Archivos de salida
            String path = inputFile.getParent();
            String filename = inputFile.getName();
            String encryptedFilePath = path + "\\" + filename;
            System.out.println(encryptedFilePath);
            File encryptedFile = new File( encryptedFilePath );

            // Cifrar
            encryptFile(publicKey, inputFile, encryptedFile);
            System.out.println("Encriptado");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean decrypt (File inputFile) {
        try {
            // Archivos de entrada y salida
            String path = inputFile.getParent();
            String filename = inputFile.getName();
            String decryptedFilePath = path + "\\" + "decrypted-" + filename;

            System.out.println(decryptedFilePath);
            File decryptedFile = new File(decryptedFilePath);

            // Descifrar
            decryptFile(privateKey, inputFile, decryptedFile);

            System.out.println("Archivo cifrado y descifrado exitosamente.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para generar un par de llaves RSA
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    // Método para guardar una llave en un archivo de texto en formato Base64
    private static void saveKeyToFile(String fileName, byte[] key) throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(key);
        try (FileWriter keyWriter = new FileWriter(fileName)) {
            keyWriter.write(encodedKey);
        }
    }

    // Método para cargar una llave pública desde un archivo de texto en formato Base64
    private static PublicKey loadPublicKeyFromFile(String fileName) throws Exception {
        byte[] keyBytes = loadKeyFromFile(fileName);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(spec);
    }

    // Método para cargar una llave privada desde un archivo de texto en formato Base64
    private static PrivateKey loadPrivateKeyFromFile(String fileName) throws Exception {
        byte[] keyBytes = loadKeyFromFile(fileName);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(spec);
    }

    // Método para cargar una llave desde un archivo de texto en formato Base64
    private static byte[] loadKeyFromFile(String fileName) throws Exception {
        String key = new String(Files.readAllBytes(Paths.get(fileName)));
        return Base64.getDecoder().decode(key);
    }

    // Método para cifrar un archivo usando RSA
    private void encryptFile(PublicKey key, File inputFile, File outputFile) throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    // Método para descifrar un archivo usando RSA
    private void decryptFile(PrivateKey key, File inputFile, File outputFile) throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private void doCrypto(int cipherMode, java.security.Key key, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode, key);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            outputStream.write(outputBytes);
        }
    }
}
