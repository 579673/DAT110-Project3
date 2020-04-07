package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(entity.getBytes());
			hashint = new BigInteger(toHex(digest), 16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashint;
	}

	public static BigInteger addressSize() {

		// Task: compute the address size of MD5
		// get the digest length
		// compute the number of bits = digest length * 8
		int bitLength = bitSize();
		// compute the address size = 2 ^ number of bits
		BigInteger adressSize = (BigInteger.ONE.add(BigInteger.ONE)).pow(bitLength);
		// return the address size
		//System.out.println("Adressize:" + adressSize);
		return adressSize;
	}

	public static int bitSize() {

		int digestlen = 0;

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digested = md.digest();

		// get the digest length
		digestlen = digested.length;
		// compute the number of bits = digest length * 8
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
