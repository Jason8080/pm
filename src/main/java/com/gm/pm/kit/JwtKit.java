package com.gm.pm.kit;

import lombok.Data;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * JWT认证工具
 *
 * @author Jason
 */
public class JwtKit {

    /**
     * 签名密钥: 不可泄露
     */
    public static String secret = "J!";

    @Data
    static class Head {
        String alg = "HmacSHA256";
        String typ = "JWT";
    }

    @Data
    static class Body {
        // 用户名
        String name;
        // 角色组
        String roles;
        // 客户端
        String ip;
        // 有效期
        Long exp;
        // JWT本身的唯一标识: 具体场景待探索
        String jti;
    }

    public static String sign(Body body) {
        return sign(new Head(), body);
    }

    public static String sign(Head head, Body body) {
        String headJson = JsonKit.toJson(head);
        String eh = Base64.getEncoder().encodeToString(headJson.getBytes());
        String bodyJson = JsonKit.toJson(body);
        String eb = Base64.getEncoder().encodeToString(bodyJson.getBytes());
        String es = encrypt(head, eh + "." + eb);
        return eh + "." + eb + "." + es;
    }

    public static Body sign(String sign) {
        String[] split = sign.split(".");
        if(split.length == 3){
            String eh = split[0];
            String eb = split[1];
            String esSource = split[2];
            byte[] head = Base64.getDecoder().decode(eh);
            String esTarget = encrypt(JsonKit.toObject(head, Head.class), eh + "." + eb);
            if(esSource.equals(esTarget)){
                byte[] body = Base64.getDecoder().decode(eb);
                return JsonKit.toObject(body, Body.class);
            }
        }
        return null;
    }

    private static String encrypt(Head head, String string) {
        try {
            Mac mac = Mac.getInstance(head.alg);
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), head.alg));
            byte[] signData = mac.doFinal(string.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(signData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decrypt(Head head, String string) {
        try {
            Mac mac = Mac.getInstance(head.alg);
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), head.alg));
            byte[] signData = mac.doFinal(string.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(signData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
