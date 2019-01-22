package cn.gzsxt.transport.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Md5Utils {
	
	private static final String salt="Pwetio!WEW345"; 
	
	/**
	 * 将密码Md5加密
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		
		
		try {
			//1.获得摘要对象
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//2.返回的是密文的字节码，转成字符串是乱码
			source=source+salt;
			byte[] result = digest.digest(source.getBytes());
			//3.为了避免存储的是乱码，可以使用base64让输出的字符串不是乱码
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		 System.out.println(md5("123"));
	}

}
