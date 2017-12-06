package io.rong.imkit;

import android.net.Uri;
import android.util.Base64;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.rong.common.RLog;

/**
 * Created by aochuang01 on 2017/3/27.
 */

public class AESCrypt {
    private static final String sn = "aochuang.com";//手机号加密用
    public static String SEED_16_CHARACTER = "";//加密的key,修改这个字段
    private static Cipher cipher;
    private static SecretKeySpec key;
    private static AlgorithmParameterSpec spec;

    public AESCrypt(String model) {
        init(model);
    }


    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据uri生产对于的加密的key
     *
     * @param uri
     * @return
     */

    public static AESCrypt generateAesc(Uri uri) {
        /**
         * 需要加密的参数要重新build
         */
        Set<String> set = uri.getQueryParameterNames();
        JSONObject jsonObject = new JSONObject();
        for (String name : set) {
            String value = uri.getQueryParameter(name);
//            LogUtils.d("name=" + name + " value=" + uri.getQueryParameter(name));
            try {
                jsonObject.put(name, value);
            } catch (JSONException e) {
                LogUtils.d("JSONException" + e);
                e.printStackTrace();
            }

        }
        //  LogUtils.d("加密前jsonObject=" + jsonObject.toString());
        AESCrypt aesCrypt = new AESCrypt(jsonObject.toString());
        return aesCrypt;


    }

    //这段异或运算用于手机号加密
    public static String getEncryption(String para) {
        char[] charArray = para.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ sn.charAt(i));
        }
        String result = "";
        try {
            result = URLEncoder.encode(String.valueOf(charArray), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Log.e("david", "getEncryption=" + result);
        return result;
    }

    private void init(String jsonString) {
        generateKey(jsonString);
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            //   LogUtils.d("SEED_16_CHARACTER=" + SEED_16_CHARACTER);
            digest.update(SEED_16_CHARACTER.getBytes("UTF-8"));
            byte[] keyBytes = new byte[32];
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            key = new SecretKeySpec(keyBytes, "AES");
            spec = getIV();
        } catch (Exception e) {
            LogUtils.d("init加密出错=" + e.toString());
            e.printStackTrace();
        }

    }

    public static void initAesc() {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            //   LogUtils.d("SEED_16_CHARACTER=" + SEED_16_CHARACTER);
            digest.update(SEED_16_CHARACTER.getBytes("UTF-8"));
            byte[] keyBytes = new byte[32];
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            key = new SecretKeySpec(keyBytes, "AES");
            spec = getIV();
        } catch (Exception e) {
            LogUtils.d("init加密出错=" + e.toString());
            e.printStackTrace();
        }
    }

    public static AlgorithmParameterSpec getIV() {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

    public static String encrypt(String plainText) {
        String encryptedText = null;
        if (cipher == null)
            initAesc();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            encryptedText = new String(Base64.encode(encrypted,
                    Base64.DEFAULT), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            RLog.e("加密", "加密失败" + e);
        }
        return encryptedText;
    }

    public static String decrypt(String cryptedText) {
        String decryptedText = null;
        if (cipher == null)
            initAesc();
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] bytes = Base64.decode(cryptedText, Base64.DEFAULT);
            byte[] decrypted = cipher.doFinal(bytes);
            decryptedText = new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.d("解密后的decryptedText=" + decryptedText);
        return decryptedText;
    }

    public String generateKey(String params) {
        String gsonparam = new Gson().toJson(params);
//        LogUtils.d("加密前的params=" + gsonparam);
        try {
            SEED_16_CHARACTER = getMd5(getMd5(gsonparam));
            //  LogUtils.d("加密的params=" + " param：" + gsonparam.toString() + " ：" + SEED_16_CHARACTER);
        } catch (Exception e) {
            LogUtils.d("加密出错=" + e.toString());
            e.printStackTrace();

        }
        return SEED_16_CHARACTER;
    }
}