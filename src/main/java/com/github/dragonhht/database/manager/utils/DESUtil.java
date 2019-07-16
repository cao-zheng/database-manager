package com.github.dragonhht.database.manager.utils;

import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 使用DES加解密的工具类.
 *
 * @author: huang
 * @Date: 2019-7-9
 */
public final class DESUtil {

    private static final Logger log = LoggerFactory.getLogger(DESUtil.class);

    private static final String SECRET_KEY_PATH = File.separator + "data" + File.separator
            + "secret" + File.separator + "key";

    private static byte[] secretKey;

    private static final DESUtil util = new DESUtil();

    private Cipher encodeCipher, decodeCipher;

    public static DESUtil getInstance() {
        return util;
    }

    private DESUtil() {
        String path = System.getProperty("user.dir") + SECRET_KEY_PATH;
        try {
            byte[] bytes = FileUtil.INSTANCE.readFile(path);
            if (bytes != null && bytes.length > 0) {
                secretKey = bytes;
            } else {
                log.info("用户未初始化加密秘钥，正在初始化加密秘钥...");
                secretKey = initSecretKey();
                FileUtil.INSTANCE.writeFile(path, secretKey, StandardOpenOption.WRITE);
            }
        } catch (IOException e) {
            log.info("用户未初始化加密秘钥，正在初始化加密秘钥...");
            secretKey = initSecretKey();
            try {
                FileUtil.INSTANCE.writeFile(path, secretKey, StandardOpenOption.WRITE);
            } catch (IOException e1) {
                log.error("用户加密秘钥保存失败", e1);
            }
        }
        try {
            SecretKey secret = revertSecretKey(secretKey);
            encodeCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 加密模式
            encodeCipher.init(Cipher.ENCRYPT_MODE, secret);
            decodeCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // 解密模式
            decodeCipher.init(Cipher.DECRYPT_MODE, secret);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                |InvalidKeySpecException | InvalidKeyException e) {
            log.error("加密工具初始化异常", e);
        }
    }

    /**
     * 还原秘钥.
     * @param key
     * @return
     */
    private SecretKey revertSecretKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(desKeySpec);
    }

    /**
     * 初始化秘钥.
     * @return
     */
    private byte[] initSecretKey() {
        // 实例化秘钥生成器
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化, 56位秘钥
        keyGenerator.init(56);
        // 生成秘钥
        SecretKey key = keyGenerator.generateKey();

        return key.getEncoded();
    }

    /**
     * 加密.
     * @param bytes
     * @return
     */
    public String encode(byte[] bytes) throws Exception {
        byte[] code =  encodeCipher.doFinal(bytes);
        return Hex.toHexString(code);
    }

    /**
     * 解密.
     * @param bytes
     * @return
     */
    public byte[] decode(byte[] bytes) throws Exception {
        return decodeCipher.doFinal(Hex.decode(bytes));
    }
}
