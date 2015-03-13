package tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * User: FR
 * Time: 3/2/15 3:18 PM
 */
public class RSA {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String ENCODING = "utf-8";

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        byte[] publicKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        byte[] privateKey =key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }


    /**
     * query RSA private key
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * query RSA public key
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        try {
            byte[] buffer= (new BASE64Decoder()).decodeBuffer(key);
            KeyFactory keyFactory= KeyFactory.getInstance(KEY_ALGORITHM);
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
            RSAPublicKey publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }


    public static String decryptMessage(String message, Key key) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            byte[] passwordBytes = (new BASE64Decoder()).decodeBuffer(message);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            int length = passwordBytes.length;
            int offset = 0;

            while (offset< length){
                byte[] encText;
                if((offset+MAX_DECRYPT_BLOCK)<length){
                    encText = cipher.doFinal(passwordBytes, offset, MAX_DECRYPT_BLOCK);
                }else {
                    encText = cipher.doFinal(passwordBytes, offset, length-offset);
                }
                out.write(encText, 0, encText.length);
                offset += MAX_DECRYPT_BLOCK;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return new String(out.toByteArray());
    }


    public static String encryptMessage(String message, Key key) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);//.init(Cipher.ENCRYPT_MODE, publicKey,, new SecureRandom(seed));
            byte [] data = message.getBytes();
            int offset = 0;
            int length = data.length;

            while (offset < length){
                byte[] encText;
                if((offset+MAX_ENCRYPT_BLOCK)<length){
                    encText = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
                }else {
                    encText = cipher.doFinal(data, offset, length-offset);
                }
                out.write(encText, 0, encText.length);
                offset += MAX_ENCRYPT_BLOCK;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return (new BASE64Encoder()).encode(out.toByteArray());
    }


    public static String signByPrivateKeyBase64(String content, PrivateKey key) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(key);
            signature.update(content.getBytes(ENCODING));
            byte[] signed = signature.sign();
            return encryptBASE64 (signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifySignByPublicKeyUrlBase64(String content, String sign, PublicKey key) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(key);
            signature.update(content.getBytes(ENCODING));
            return signature.verify(decryptBASE64(sign));
        } catch (Exception e) {
            // ignore exception
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap;
        keyMap = initKey();
        String publicKey = getPublicKey(keyMap);
        publicKey=publicKey.replaceAll("\\n","");
        System.out.println(publicKey);
        String privateKey = getPrivateKey(keyMap);
        privateKey=privateKey.replaceAll("\\n","");
        System.out.println(privateKey);

        String msg = encryptMessage("test", getPublicKey(publicKey));
        String msg2 = decryptMessage(msg, getPrivateKey(privateKey));
        System.out.println("test".equals(msg2));
    }

    public static void m() throws Exception {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK3puS43O5f6p7T7P5u6aH48eket" +
                "fo9FURSwSNlIooVxGKCb1xNWhsciVxU+3VNwaxWrEuH+lfi7I/Ork0U2g9VH0qCGVsALsRUrjg5x" +
                "Nhofpu0po5yaKAqie9iF3juNANoYyYpFmHyCY8qNYE554vzg+1z7zA9KaXW65ALb8najAgMBAAEC" +
                "gYA2F5whY8yFWNEQ2ZTPOwuFOApQgQX8SfMOHEaMTqs+Fg8y5UXgHemNxoCPex4b9btY4fzdjfHX" +
                "i12DTLphrLMT/oNmf9tevHm1jfq9O2O/R+SXKLEoDQf4OAe94j/hvUPda3vMk7YeCSxeXrigcrpW" +
                "F0dIum2hI+8hv54ohjFyAQJBAPbM+MsFa5R0o7Yid1jFcRD1sywHAEC4o5Of1vgkuf2jEUmPylDE" +
                "91tkMMYaiPFQjQ90K0cbtXM99hs+Ab145EkCQQC0ZT2X7a4wMpJRC80SZkdA4RAHJwzmS5GoYSgC" +
                "TIukmIa1MDRO0Hvq/LFZq9xMWt22ooWtk3iQoslikDNMxGuLAkANSDSvgGgswiY5XBt+LIoMNpzg" +
                "5rONyCINhNGl4xwbWVZJ1jI2LJN/WKIDV1Q9YG9nAIJ/vi8Ct7fx+WFQmBv5AkEApX7rzuICl70N" +
                "dsT40hR68g6IAcfVkcFqU6suZU2MHui/TKJ4moWx6ZcBrV55sKL8HH1lXiSYyzhMwNvB9H+OmQJA" +
                "IQ/rlA6kKvPwivEuPUipr/fq4CQqYPmDJSrpbNG73JzW/bXlnTnQ58Dw0W+TJGupXVmmheHT3RGR" +
                "fTtSY58oMg==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt6bkuNzuX+qe0+z+bumh+PHpHrX6PRVEUsEjZ" +
                "SKKFcRigm9cTVobHIlcVPt1TcGsVqxLh/pX4uyPzq5NFNoPVR9KghlbAC7EVK44OcTYaH6btKaOc" +
                "migKonvYhd47jQDaGMmKRZh8gmPKjWBOeeL84Ptc+8wPSml1uuQC2/J2owIDAQAB";
        String msg = encryptMessage("test", getPrivateKey(privateKey));
        for(int i=0; i<10; i++){
            String msg3 = encryptMessage("test", getPrivateKey(privateKey));
            if(!msg3.equals(msg)){
                System.out.println("pricate diff");
            }
        }
        msg = encryptMessage("test", getPublicKey(publicKey));
        for(int i=0; i<10; i++){
            String msg3 = encryptMessage("test", getPublicKey(publicKey));
            if(!msg3.equals(msg)){
                System.out.println("pub diff");
            }
        }
    }

}
