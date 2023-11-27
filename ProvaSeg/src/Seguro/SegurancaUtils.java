package Seguro;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SegurancaUtils {
    public static String criptografa(String data, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);

        // Criptografar os dados
        byte[] dadosCriptografados = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Converter os dados criptografados para Base64 para facilitar a representação
        return Base64.getEncoder().encodeToString(dadosCriptografados);
    }

    public static String decriptografa(String dataCriptografada, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, chave);

        // Decodificar os dados criptografados de Base64 para obter o array de bytes original
        byte[] decodedData = Base64.getDecoder().decode(dataCriptografada);

        // Descriptografar os dados
        byte[] decryptedBytes = cipher.doFinal(decodedData);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static SecretKey criarChaveSecreta(String chave) {
        byte[] chaveBytes = chave.getBytes(StandardCharsets.UTF_8);
        // Limitando a chave para 8 bytes (64 bits) para DES
        byte[] chaveBytes8 = new byte[8];
        System.arraycopy(chaveBytes, 0, chaveBytes8, 0, Math.min(chaveBytes.length, 8));

        // Convertendo os bytes da chave para um SecretKey usando SecretKeySpec
        return new SecretKeySpec(chaveBytes8, "DES");
    }

    public static String gerarHash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(senha.getBytes());

            // Converter o array de bytes em representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
