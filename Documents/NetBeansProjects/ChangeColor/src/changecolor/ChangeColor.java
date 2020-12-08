/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changecolor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author HL
 */
public class ChangeColor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChangeColor changeColor = new ChangeColor();
        changeColor.changeColor();

    }

    private void changeColor() {
        BufferedImage img = readImage("C:\\Users\\HL\\Desktop\\images\\image.bmp");

        int w = img.getWidth();
        int h = img.getHeight();
        Raster raster = img.getRaster();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                // byte 1 alpha
                // byte 2 green
                // byte 3 red
                // byte 4 blue
                /*
                g = 0x00ff0000
                r = 0x0000ff00
                b = 0x000000ff
                a = 0xff000000
                */
                
                int p = img.getRGB(i, j);
                int a = (p) & 0xff;
                int g = (p >> 8) & 0xff;// shift 8 byte and split 

//                int g = (p>>16)&0xff;
                int b = (p >> 24) & 0xff;
                p = (a << 24) | (g << 8) | b;

//                 p=  (b<<24)| (g<<16) | (r<<8) | a ;
                img.setRGB(i, j, p);

                bi.setRGB(i, j, p);
//                System.out.print(p+" ");
//                System.out.println(red);
//                System.out.println(raster.getSample(i, j, 0));
//                img.setRGB(i, j, new Color(c.getRed(), c.getGreen(),c.getBlue()).getRGB());

            }
        }

        if (writeImage(bi)) {
            System.out.println("succes");
        } else {
            System.out.println("fail");
        }

    }

    private boolean writeImage(BufferedImage img) {

        try {
            ImageIO.write(img, "BMP", new File("C:\\Users\\HL\\Desktop\\images\\out.bmp"));
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    private BufferedImage readImage(String url) {
        File file = new File(url);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);

            if (image != null) {
                return image;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
