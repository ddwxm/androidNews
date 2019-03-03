package somnus.example.com.minghuaapp.util;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import somnus.example.com.minghuaapp.model.User;

/**
 * Created by Somnus on 2019/2/13.
 * .AES加密算法
 */

public class AesUtil {

    private String INSTANCE = "AES";
    private String key = null;

    public AesUtil(){
        this.key = ConstantsUtil.AES_KEY;
    }

    /**
     * 加密
     * @param string
     * @return
     * @throws Exception
     */
    public String encrypt(String string) throws Exception{
        // 创建AES秘钥
        SecretKeySpec password = new SecretKeySpec(key.getBytes(), "AES/ECB/PKCS5PADDING");
        // 创建密码器
        Cipher cipher = Cipher.getInstance(INSTANCE);
        // 初始化加密器
        cipher.init(Cipher.ENCRYPT_MODE, password);
        // 加密
        return BinUtil.byte2str(cipher.doFinal(string.getBytes("UTF-8")));
    }

    /**
     * 解密
     * @param string
     * @return
     * @throws Exception
     */
    public String decrypt(String string) throws Exception{
        byte[] bytes = BinUtil.str2byte(string);
        // 创建AES秘钥
        SecretKeySpec password = new SecretKeySpec(key.getBytes(), "AES/ECB/PKCS5PADDING");
        // 创建密码器
        Cipher cipher = Cipher.getInstance(INSTANCE);
        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, password);
        // 解密
        return new String(cipher.doFinal(bytes),"UTF-8");
    }

    /**
     * 获取缓存用户信息
     * @param string 用户本地缓存
     * @return USER对象
     */
    public User getUser(String string){
        try {
            string = decrypt(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(string,User.class);
    }

}
