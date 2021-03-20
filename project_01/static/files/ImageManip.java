//    Image Filter Application

import java.awt.image.BufferedImage;// creates the image buffer
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.imageio.ImageIO;// Does Image writing 
import java.io.IOException;
import java.io.File;
public class ImageManip
{
    public static File imageFile;
    public static BufferedImage image;
    public static boolean flag = false;
    public static String fileName, extension="";
    public static int a, r, b, g, pixel, width, height;
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void writeImage(String head) throws IOException
    {
        imageFile = new File(head+fileName);
        if(imageFile.exists() == false)
            imageFile.createNewFile();
        ImageIO.write(image, extension, imageFile);   
    }
    public static boolean openImage() throws IOException
    {
        imageFile = new File(fileName);
        if(imageFile.exists() == false)
        {
            System.out.println("File Does Not Exist!!!");
            return false; 
        }
        image = ImageIO.read(imageFile);
        width = image.getWidth();
        height = image.getHeight();
        return true;
    }
    public static void extract(int pixel)
    {
        a = pixel>>24 & 0xff;
        r = pixel>>16 & 0xff;
        g = pixel>>8 & 0xff;
        b = pixel>>0 & 0xff;
    }
    public static boolean green() throws IOException
    {
        openImage();
        for(int x = 0; x<width; x++)
        {
            for(int y = 0; y<height; y++)
            {
                pixel = image.getRGB(x, y);
                extract(pixel);
                pixel = (a << 24) | (0 << 16) | (g << 8) | 0;
                image.setRGB(x, y, pixel);
            } 
        }
        writeImage("green_");
        return true;
    }    
    public static boolean blue() throws IOException
    {
        openImage();
        for(int x = 0; x<width; x++)
        {
            for(int y = 0; y<height; y++)
            {
                pixel = image.getRGB(x, y);
                extract(pixel);
                pixel = (a << 24) | (0 << 16) | (0 << 8) | b;
                image.setRGB(x, y, pixel);
            } 
        }
        writeImage("blue_");
        return true;
    }
    public static boolean red() throws IOException
    {
        openImage();
        for(int x = 0; x<width; x++)
        {
            for(int y = 0; y<height; y++)
            {
                pixel = image.getRGB(x, y);
                extract(pixel);
                pixel = (a << 24) | (r << 16) | (0 << 8) | 0;
                image.setRGB(x, y, pixel);
            } 
        }
        writeImage("red_");
        return true;
    }
    public static boolean inverse() throws IOException
    {
        openImage();
        for(int x = 0; x<width; x++)
        {
            for(int y = 0; y<height; y++)
            {
                pixel = image.getRGB(x, y);
                extract(pixel);
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                pixel = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, pixel);
            } 
        }
        writeImage("inverse_");
        return true;
    } 
    public static boolean grayScale() throws IOException
    {
        openImage();
        for(int x = 0; x<width; x++)
        {
            for(int y = 0; y<height; y++)
            {
                pixel = image.getRGB(x, y);
                extract(pixel);
                r = g = b = (r+g+b)/3;
                pixel = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, pixel);
            } 
        }        
        writeImage("grayScale_");
        return true;       
    }
    public static void main(String[] args) throws IOException
    {
        System.out.println("Enter the image File Name");
        fileName = in.readLine();
        if(fileName.length() < 1)
        {
            fileName="Nature.png";
            System.out.println("No name given.........\nusing "+fileName);
        }
        for(int index = 0; index < fileName.length(); index++)
        {
            if(flag)
                extension=extension+fileName.charAt(index);
            if(fileName.charAt(index) == '.')
                flag = true;
        }
        if(inverse() && grayScale() && red() && blue() && green())
            System.out.println("Image Conversion Is Completed :) "+fileName);
        else
            System.out.println("Error in conversion!!! ");
    }
}
