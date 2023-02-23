package hu.ponte.hr.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Service
public class SignService {

    @Value("${private.key}")
    private String privateKey;

    public byte[] signImage(byte[] file) {
        try {
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKey));

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            String algorithm = "SHA256withRSA";
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(file);

            byte[] digitalSignature = signature.sign();

            return digitalSignature;
        } catch (IOException |
                 InvalidKeySpecException |
                 NoSuchAlgorithmException |
                 SignatureException |
                 InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
