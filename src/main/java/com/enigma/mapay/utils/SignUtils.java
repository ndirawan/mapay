package com.enigma.mapay.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SignUtils {
    @Value("${secret.username}")
    private String username;
    @Value("${secret.api.key}")
    private String key;
    public String generateSignature(String refId) {
        try {
            String md5 = username + key + refId;
            String ok = generateMd5Hash(md5);
            return ok;
        }catch (NoSuchAlgorithmException e){
            e.getMessage();
        }
        return null;
    }
    public static String generateMd5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest);
        return myHash.toLowerCase();
    }
}
