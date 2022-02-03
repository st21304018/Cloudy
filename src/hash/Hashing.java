package hash;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing{
	public static String hashing(String code){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashCode = md5.digest(code.getBytes());

		return String.format("%020x", new BigInteger(1, hashCode));
	}
}
