import org.apache.shiro.crypto.hash.SimpleHash;

public class Shiros {
	public static void main(String[] args) {
		int hashIterations = 10000;//加密的次数
		Object salt = "zhao";//盐值
		Object credentials = "123456";//密码
		String hashAlgorithmName = "SHA-256";//加密方式
		Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
				salt, hashIterations);
		System.out.println("加密后的值----->" + simpleHash);
	}
}
