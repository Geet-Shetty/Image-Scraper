package scraper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageScraper extends GUI {

	
	public static List<String> names = new ArrayList<>(); 
	private static int count;

	
	static void ConnectAndPull(String url, String folder) {
		int count = 0;
		try {
			Document doc = Jsoup.connect(url).get();
	        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
	        System.out.println("Connected");
	        for (Element image : images) {
	       	 
	       	 String imglink = image.attr("src");
	            if (imglink.contains("jpeg")) {
	           	 imglink = imglink.substring(0, imglink.indexOf("jpeg")+4);
	            }
	            else if (imglink.contains("jpg")) {
	           	 imglink = imglink.substring(0, imglink.indexOf("jpg")+3);
	            }
	            else if (imglink.contains("png")) {
	           	 imglink = imglink.substring(0, imglink.indexOf("png")+3); 
	            }
	            else if (imglink.contains("gif")) {
	           	 imglink = imglink.substring(0, imglink.indexOf("gif")+3);
	            }
	          	 System.out.println("\nsrc : " + imglink);
	             
	             downloadImage(imglink, folder);
	             
	             count++;
	        }
	      
        }
		 catch(IOException e) {
//	        	e.printStackTrace();
			 System.out.println("F");
		 }
		
		System.out.println(count);
        }
            

	
	static void downloadImage(String strImageURL, String Folder){
	        
	        //get file name from image path
	        String strImageName = 
	                strImageURL.substring(strImageURL.lastIndexOf("/") + 1 );
	        
	        names.add(strImageName);
	        
	        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);
	        
	        try {
	            
	            //open the stream from URL
	        	URL urlImage = new URL(strImageURL);
	            InputStream in = urlImage.openStream();
	            
	            byte[] buffer = new byte[4096];
	            int n = -1;
	            
	            OutputStream os = new FileOutputStream(Folder + "/" + strImageName );
	            
	            //write bytes to the output stream
	            while ( (n = in.read(buffer)) != -1 ){
	                os.write(buffer, 0, n);
	            }
	            
	            //close the stream
	            os.close();
	            
	            System.out.println("Image saved");
	            
	        } catch (IOException e) {
//	            e.printStackTrace();
	        	System.out.println("F");
	        }
	        
	    }


}
