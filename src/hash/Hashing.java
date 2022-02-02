package hash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Hashing{
	public static byte[] hashing(String code){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashCode = md5.digest(code.getBytes());

		return hashCode;
	}
}
