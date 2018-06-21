package com.psl.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Test {
	static List<Long> list = new ArrayList<Long>();
	public static void main(String[] args) throws IOException {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		System.out.println(calendar.getTime().toString());
		 StringBuffer defaultPathSb = new StringBuffer();
		 defaultPathSb.append("/");
		       defaultPathSb.append("process-instances/");
		       defaultPathSb.append(calendar.get(1));
		     defaultPathSb.append("/");
		       int month = calendar.get(2) + 1;
		       if(month < 10)

		           defaultPathSb.append("0");

		      defaultPathSb.append(month);
		        defaultPathSb.append("/");

		        int day = calendar.get(5);
		       if(day < 10)

	           defaultPathSb.append("0");

		       defaultPathSb.append(day);
		       defaultPathSb.append("/");

		        int hour = calendar.get(11);
	      if(hour < 10)

		           defaultPathSb.append("0");

		        defaultPathSb.append(hour);
		        defaultPathSb.append("/");

		        defaultPathSb.append("pi-");
		         defaultPathSb.append(1000);
		 System.out.println(defaultPathSb);
		/*byte[] rawBytes={1,2,3,4,5,6,7,8};
		byte[] array = new byte[1024*1000];
		String fileName= "Sample.txt";
        System.out.println(Arrays.asList(rawBytes));
        System.out.println(new String(rawBytes));
        File f = new File("Address Proof.pdf");
       FileInputStream fis = new FileInputStream(f);  
       //List<Byte> bytes = Arrays.asList(array);
       fis.read(array);
       for(byte b : array){
    	   System.out.print(b);
       }
       System.out.println();
       System.out.println( Arrays.toString(array));
       //String response = "{\"fileName\":"+fileName+",\n \"documentContent\":"+Arrays.toString(array)+"}";
      // System.out.println(response);
       
       String bytes = null;
   	try {
   		bytes = new String(rawBytes,"UTF-8");
   	} catch (UnsupportedEncodingException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   	//System.out.println(bytes);
   	int l = array.length;
   	int[] arr = new int[l];
   	int i=0;
   	for(Byte  b : array){
   	
   		arr[i++] = (0x000000FF) & b;
 		//arr[i++] = b.intValue();
   		
   	}
    System.out.println(Arrays.toString(arr));

    //	FileOutputStream fos = new FileOutputStream(new File("abc.pdf"));
    	//fos.write(array);
    */
    list.add(1L);
    list.add(2L);
    String body = "{ processOids :"+list+"}";
    System.out.println(body);
    
	}
	
}
