package gz.itcast.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String md5(String password){
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] result = md.digest(password.getBytes());
			StringBuffer md5Psw = new StringBuffer();
			for(byte b: result){
				int i = 0;
				if(b<0){
					//负值
					i=b+256;
				}else{
					//正值
					i=b;
				}
				
				if(i<16){
					md5Psw.append("0"+Integer.toHexString(i));
				}else{
					md5Psw.append(Integer.toHexString(i));
				}
				
			}
			return md5Psw.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
