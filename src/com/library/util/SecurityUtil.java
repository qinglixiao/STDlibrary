package com.library.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

import com.library.core.Base64;

public class SecurityUtil {
	public static class MD5 {
		/**
		 * 
		 * ��          �� �������ַ���
		 * ��������  : 2014-7-28
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param plaintext 
		 * @return
		 *
		 */
		public static String encrypt(String plaintext) {
			try {
				MessageDigest s_md5 = MessageDigest.getInstance("MD5");
				s_md5.update(plaintext.getBytes(Charset.forName("UTF8")));
				byte[] digest = s_md5.digest();
				return byte2hex(digest);
			}
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				return "-1";
			}
		}

		// ������ת�ַ���
		private static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
				if (n < b.length - 1)
					hs = hs + ":";
			}
			return hs.toUpperCase();
		}
	}

	public static class SHA1 {
		/**
		 * 
		 * ��          �� �������ַ���
		 * ��������  : 2014-7-28
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param plaintext 
		 * @return
		 *
		 */
		public static String encrypt(String plaintext) {
			try {
				MessageDigest s_shal = MessageDigest.getInstance("SHA-1");
				s_shal.update(plaintext.getBytes(Charset.forName("UTF8")));
				byte[] digest = s_shal.digest();
				return byte2hex(digest);
			}
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				return "-1";
			}
		}

		// ������ת�ַ���
		private static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
				if (n < b.length - 1)
					hs = hs + ":";
			}
			return hs.toUpperCase();
		}

	}

	public static class AES {
		/**
		 * 
		 * ��          �� ������
		 * ��������  : 2014-7-12
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param pwd ����
		 * @param plaintext ����
		 * @return
		 *
		 */
		public static String encrypt(String pwd, String plaintext) {
			try {
				byte[] rawKey = getRawKey(pwd.getBytes());
				byte[] result = encrypt(rawKey, plaintext.getBytes());
				return toHex(result);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		}

		/**
		 * 
		 * ��          �� ������
		 * ��������  : 2014-7-12
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param pwd ����
		 * @param encryptext ����
		 * @return
		 *
		 */
		public static String decrypt(String pwd, String encryptext){
			try {
				byte[] rawKey = getRawKey(pwd.getBytes());
				byte[] enc = toByte(encryptext);
				byte[] result = decrypt(rawKey, enc);
				return new String(result);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		}

		private static byte[] getRawKey(byte[] seed) throws Exception {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(seed);
			kgen.init(128, sr); // 192 and 256 bits may not be available   
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();
			return raw;
		}

		private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(clear);
			return encrypted;
		}

		private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		}

		public static String toHex(String txt) {
			return toHex(txt.getBytes());
		}

		public static String fromHex(String hex) {
			return new String(toByte(hex));
		}

		public static byte[] toByte(String hexString) {
			int len = hexString.length() / 2;
			byte[] result = new byte[len];
			for (int i = 0; i < len; i++)
				result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
			return result;
		}

		public static String toHex(byte[] buf) {
			if (buf == null)
				return "";
			StringBuffer result = new StringBuffer(2 * buf.length);
			for (int i = 0; i < buf.length; i++) {
				appendHex(result, buf[i]);
			}
			return result.toString();
		}

		private final static String HEX = "0123456789ABCDEF";

		private static void appendHex(StringBuffer sb, byte b) {
			sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
		}
	}
	
	public static class RSA {
		/**ǩ���㷨*/
		private static final String SIGN_ARITHMETIC = "SHA1WithRSA";

		/**
		 * 
		 * ��          �� �����ַ�������ǩ��
		 * ��������  : 2014-7-9
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param content Ҫǩ�����ַ���
		 * @param privateKey ����Base64�������˽Կ
		 * @return ǩ������Base64�����ַ���
		 *
		 */
		public static String sign(String plaintext, String privateKey) {
			try {
				PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
				KeyFactory keyf = KeyFactory.getInstance("RSA");
				PrivateKey priKey = keyf.generatePrivate(priPKCS8);

				java.security.Signature signature = java.security.Signature.getInstance(SIGN_ARITHMETIC);
				signature.initSign(priKey);
				signature.update(plaintext.getBytes("UTF-8"));
				byte[] signed = signature.sign();
				return Base64.encode(signed);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("RSASecureUtil", e.getMessage());
				return "";
			}

		}

		/**
		* RSAǩ�����
		* @param content δǩ����ԭ��
		* @param sign ǩ����
		* @param publicKey ��Կ
		* @return
		* 	true: ԭ��һ��
		* 	false:��һ��
		*/
		public static boolean verify(String plaintext, String sign, String publicKey) {
			try {
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				byte[] encodedKey = Base64.decode(publicKey);
				PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

				java.security.Signature signature = java.security.Signature.getInstance(SIGN_ARITHMETIC);
				signature.initVerify(pubKey);
				signature.update(plaintext.getBytes("UTF-8"));

				boolean bverify = signature.verify(Base64.decode(sign));
				return bverify;

			}
			catch (Exception e) {
				Log.e("RSASecureUtil", e.getMessage());
				return false;
			}
		}

		/**
		 * 
		 * ��          �� ������
		 * ��������  : 2014-7-9
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param encryptext ����
		 * @param privateKey ˽Կ
		 * @return
		 * @throws Exception
		 *
		 */
		public static String decrypt(String encryptext, String privateKey) {
			try {
				byte[] encryptedData = Base64.decode(encryptext);
				PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);

				//rsa���ܵ��ֽڴ�С�����128������Ҫ���ܵ����ݣ���128λ�𿪽���
				int decrylength = 128;
				Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
				cipher.init(Cipher.DECRYPT_MODE, privateK);
				int inputLen = encryptedData.length;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int offSet = 0;
				byte[] cache;
				int i = 0;
				// �����ݷֶν���
				while (inputLen - offSet > 0) {
					if (inputLen - offSet > decrylength) {
						cache = cipher.doFinal(encryptedData, offSet, decrylength);
					}
					else {
						cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
					}
					out.write(cache, 0, cache.length);
					i++;
					offSet = i * decrylength;
				}
				byte[] decryptedData = out.toByteArray();
				out.close();
				return new String(decryptedData, "UTF-8");
			}
			catch (Exception e) {
				return "";
			}
		}

		/**
		 * 
		 * ��          �� ������
		 * ��������  : 2014-7-10
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @param plaintext ����
		 * @param publicKey ��Կ������Base64���룩
		 * @return
		 *
		 */
		public static String encrypt(String plaintext, String publicKey) {
			try {
				byte[] data = plaintext.getBytes(Charset.forName("UTF-8"));
				X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				PublicKey publicK = keyFactory.generatePublic(x509KeySpec);

				//RSA������117���ַ���������÷ֶμ���
				int encryLength = 117;
				Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
				cipher.init(Cipher.ENCRYPT_MODE, publicK);
				int inputLen = data.length;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int offSet = 0;
				byte[] cache;
				int i = 0;
				// �����ݷֶμ���
				while (inputLen - offSet > 0) {
					if (inputLen - offSet > encryLength) {
						cache = cipher.doFinal(data, offSet, encryLength);
					}
					else {
						cache = cipher.doFinal(data, offSet, inputLen - offSet);
					}
					out.write(cache, 0, cache.length);
					i++;
					offSet = i * encryLength;
				}
				byte[] encryptedData = out.toByteArray();
				out.close();
				return Base64.encode(encryptedData);
			}
			catch (Exception e) {
				Log.e("RSASecureUtil", e.getMessage());
				return "";
			}
		}

		/**
		 * 
		 * ��          �� ������RSA��Կ��(����Base64����)
		 * ��������  : 2014-7-9
		 * ��           �� �� lx
		 * �޸�����  : 
		 * ��   ��   �� ��
		 * @version   : 1.0
		 * @return
		 * ��Կ�ԣ�
		 * 	˽Կkey:"privateKey"
		 * 	��Կkey:"publicKey"
		 */
		public static Map<String, String> generateKeyPair() {
			try {
				HashMap<String, String> map = new HashMap<String, String>();
				KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
				generator.initialize(1024);
				KeyPair pair = generator.generateKeyPair();
				map.put("privateKey", Base64.encode(pair.getPrivate().getEncoded()));
				map.put("publicKey", Base64.encode(pair.getPublic().getEncoded()));
				return map;
			}
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				Log.e("RSASecureUtil", e.getMessage());
				return null;
			}

		}

	}
}
