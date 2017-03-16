package com.mina.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * 对文件和字符串的MD5校验工具类
 */
public class Md5Utils {

	protected static MessageDigest messagedigest = null;
	/**
	 * 默认的密码字符串组合， 用来将字节转换成 16 进制表示的字符， apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			messagedigest = null;
		}
	}

	/**
	 * 生成字符串的md5校验值
	 * 
	 * @param str
	 * @return
	 */
	public static String getMd5String(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		return getMd5String(str.getBytes());
	}

	/**
	 * 生成字符串的md5校验值
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getMd5Bytes(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		return getMd5Bytes(str.getBytes());
	}

	/**
	 * 判断字符串的md5校验码是否与一个已知的md5码相匹配
	 * 
	 * @param str
	 *            要校验的字符串
	 * @param md5Str
	 *            已知的md5校验码
	 * @return
	 */
	public static boolean checkStringMd5(String str, String md5Str) {
		if (str == null || "".equals(str) || md5Str == null
				|| "".equals(md5Str)) {
			return false;
		}
		try {
			String md5 = getMd5String(str);
			return md5.equals(md5Str.trim());
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 生成文件的md5校验值
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMd5String(File file) {
		if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			synchronized (messagedigest) {
				while ((numRead = fis.read(buffer)) > 0) {
					messagedigest.update(buffer, 0, numRead);
				}
				fis.close();
				return bufferToHex(messagedigest.digest());
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 生成文件的md5校验值
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getFileMd5Bytes(File file) {
		if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			synchronized (messagedigest) {
				while ((numRead = fis.read(buffer)) > 0) {
					messagedigest.update(buffer, 0, numRead);
				}
				fis.close();
				return messagedigest.digest();
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 判断文件的md5校验码是否与一个已知的md5码相匹配
	 * 
	 * @param file
	 *            要校验的文件
	 * @param md5Str
	 *            已知的md5校验码
	 * @return
	 */
	public static boolean checkFileMd5(File file, String md5Str) {
		if (md5Str == null || "".equals(md5Str)) {
			return false;
		}
		try {
			String md5 = getFileMd5String(file);
			return md5.equals(md5Str.trim());
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 生成文件的md5校验值 不推荐使用
	 * JDK1.4中不支持以MappedByteBuffer类型为参数update方法，并且网上有讨论要慎用MappedByteBuffer，
	 * 原因是当使用 FileChannel.map 方法时，MappedByteBuffer 已经在系统内占用了一个句柄， 而使用
	 * FileChannel.close 方法是无法释放这个句柄的，且FileChannel有没有提供类似 unmap 的方法，
	 * 因此会出现无法删除文件的情况。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMd5_old(File file) {
		if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
			return null;
		}
		try {
			FileInputStream in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
					0, file.length());
			in.close();
			synchronized (messagedigest) {
				messagedigest.update(byteBuffer);
				return bufferToHex(messagedigest.digest());
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 生成字节串的md5校验值
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getMd5String(byte[] bytes) {
		synchronized (messagedigest) {
			messagedigest.update(bytes);
			return bufferToHex(messagedigest.digest());
		}
	}

	/**
	 * 生成字节串的md5校验值
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] getMd5Bytes(byte[] bytes) {
		synchronized (messagedigest) {
			messagedigest.update(bytes);
			return messagedigest.digest();
		}
	}

	/**
	 * 将数字节组转换为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		// 取字节中低 4 位的数字转换
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
}
