package com.psl.utilities;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.media.jai.RenderedImageAdapter;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.ImgRaw;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;

public class TiffConversion {

	public static void main(String[] args) throws IOException, DocumentException {


//String fileName= "multipage_tif_example.tif";
//String fileName = "MARBIBM.tif";
//String fileName = "Multi_page.tif";
String fileName = "sample.tif";
//String fileName = "";
//String fileName = "";

convertMultiStripTif(fileName);

	}


	static void  convertMultiStripTif(String fileName) throws IOException, DocumentException{

		
		File file = new File(fileName);
		byte[] bytesArray = new byte[(int) file.length()]; 
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray); //read file into bytes[]
		fis.close();

		InputStream inputStream = new ByteArrayInputStream(bytesArray);
		ImageDecoder tiffDecoder = ImageCodec.createImageDecoder("tiff", inputStream, null);
		int numberOfPages = tiffDecoder.getNumPages();

		Document TifftoPDF=new Document();
		PdfWriter pdfWriter =  PdfWriter.getInstance(TifftoPDF, new FileOutputStream(fileName+".pdf"));
		pdfWriter.setStrictImageSequence(true);
		TifftoPDF.open();
		// float height = 0;
		// float width = 0;
		for(int i=0;i<numberOfPages;i++){

			RenderedImage rawImageData = tiffDecoder.decodeAsRenderedImage(i);
			RenderedImageAdapter planarImage = new RenderedImageAdapter(rawImageData);
			BufferedImage page =  planarImage.getAsBufferedImage();
			Image tempImage = Image.getInstance(page, null);
			System.out.println(tempImage instanceof ImgRaw);
			tempImage.setAbsolutePosition(0,0);
			Rectangle pageSize = new Rectangle(tempImage.getWidth(),
					tempImage.getHeight());
			TifftoPDF .setPageSize(pageSize);
			TifftoPDF.newPage();

			// float height = tempImage.getHeight();
			// float width = tempImage.getWidth();
			// tempImage.scaleToFit(width, height);
			// tempImage.scaleToFit(0, 0);



			TifftoPDF.add(tempImage);
			//System.out.println("Added page: "+i + " width: "+width+" height: "+height);
			//height += PageSize.A4.getHeight();
			//width += PageSize.A4.getWidth();

		}
		TifftoPDF.close();
		System.out.println("Tiff to PDF Conversion in Java Completed" );



	}

	void convertSingleStripTif() throws IOException, DocumentException{
		File f = new File("multipage_tif_example.tif");
		System.out.println(f.getAbsolutePath());
		RandomAccessFileOrArray myTiffFile=new RandomAccessFileOrArray("multipage_tif_example.tif");
		int numberOfPages=TiffImage.getNumberOfPages(myTiffFile);
		System.out.println("No Of pages: "+ numberOfPages);
		// TiffImage.getTiffImage(myTiffFile,1).setAbsolutePosition(0,	0);
		// Document TifftoPDF=new Document(TiffImage.getTiffImage(myTiffFile,1));
		Document TifftoPDF=new Document();
		PdfWriter pdfWriter =  PdfWriter.getInstance(TifftoPDF, new FileOutputStream("MARBIBM.pdf"));
		pdfWriter.setStrictImageSequence(true);
		TifftoPDF.open();
		// float height = 0;
		// float width = 0;
		for(int i=1;i<=numberOfPages;i++){

			Image tempImage=TiffImage.getTiffImage(myTiffFile, i);

			System.out.println(tempImage instanceof ImgRaw);
			tempImage.setAbsolutePosition(0,0);
			Rectangle pageSize = new Rectangle(tempImage.getWidth(),
					tempImage.getHeight());
			TifftoPDF .setPageSize(pageSize);
			TifftoPDF.newPage();

			// float height = tempImage.getHeight();
			// float width = tempImage.getWidth();
			// tempImage.scaleToFit(width, height);
			// tempImage.scaleToFit(0, 0);



			TifftoPDF.add(tempImage);
			//System.out.println("Added page: "+i + " width: "+width+" height: "+height);
			//height += PageSize.A4.getHeight();
			//width += PageSize.A4.getWidth();

		}
		TifftoPDF.close();
		System.out.println("Tiff to PDF Conversion in Java Completed" );
		System.out.println(f.exists());
		String str = "MARB.IBM.TIF";
		System.out.println(str);
		System.out.println(str.lastIndexOf("."));
		System.out.println(str.substring(0, str.lastIndexOf('.')));
		String[] name = str.split("\\.");
		System.out.println(name.length);
		//System.out.println(name[0] + " --- "+name[1]);
	}

}
