package com.psl.main;

import java.io.IOException;
import java.util.zip.DataFormatException;

import com.psl.utilities.CompressionUtility;

public class CompressionTest{

	public static void main(String[] args) {
		
		CompressionUtility cu = new CompressionUtility();
		byte [] decompressed={};
		byte[] compressed ={};
	    byte[] data = {37, 80, 68, 70, 45, 49, 46, 52, 10, 37, -57, -20, -113, -94, 10, 52, 32, 48, 32, 111, 98, 106, 10, 60, 60, 47, 76, 105, 110, 101, 97, 114, 105, 122, 101, 100, 32, 49, 47, 76, 32, 57, 48, 54, 49, 53, 47, 72, 91, 32, 56, 56, 50, 49, 56, 32, 49, 52, 49, 93, 47, 79, 32, 54, 47, 69, 32, 56, 56, 50, 49, 56, 47, 78, 32, 49, 47, 84, 32, 57, 48, 52, 57, 52, 62, 62, 10, 101, 110, 100, 111, 98, 106, 10, 32, 32, 32, 32, 32, 32, 32, 32};
		try {
		compressed = cu.compress(data);
		decompressed = cu.decompress(compressed);
		} catch (IOException | DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Compressed");
		for(byte b : compressed){
			System.out.print(b+", ");
		}
		System.out.println("Decompressed");
		for(byte b:decompressed){
			System.out.print(b+", ");
		}

	}

}
