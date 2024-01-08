package com.example.ProjectXpress.hash;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PaymentHash {

        public static String calculateHMAC512(String data, String key) {
            String HMAC_SHA512 = "HmacSHA512";

            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
            Mac mac = null;
            try {
                mac = Mac.getInstance(HMAC_SHA512);
                mac.init(secretKeySpec);

                return Hex.encodeHexString(mac.doFinal(data.getBytes()));
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }
