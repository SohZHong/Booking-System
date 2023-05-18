package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadImage {
    
    public static final String COMPANY_LOGO = "Logo.png";
    private static final String IMAGE_FOLDER = "/images/";
    
    //Method to obtain resized & scaled image
    public static BufferedImage getScaledImage (String fileName, int width, int height){
        
        BufferedImage img = null;
        
        InputStream is = LoadImage.class.getResourceAsStream(IMAGE_FOLDER + fileName);
        
        //try catch because path might not exist and trigger an error
        try {
            
            Image tempImg = ImageIO.read(is);
            
            img = LoadImage.createResizedCopy(tempImg, width, height); 
            
            
        } 
        catch (IOException ex) {
            System.out.println("File at location " + is + " not found");
        }
        
        return img;
    }

    private static BufferedImage createResizedCopy(Image srcImg, int width, int height) {
        
        BufferedImage scaledImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = scaledImg.createGraphics();

        g.drawImage(srcImg, 0, 0, width, height, null);
        g.dispose();
        
        return scaledImg;
        
    }
    
}
