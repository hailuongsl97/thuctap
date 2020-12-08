/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changecolor.cach2;

import java.io.FileInputStream;

/**
 *
 * @author HL
 */
public class ReadFile {
      private static final int BYTE_HEAD=14;//bmp file header 0-13
    private static final int BTYE_INFO=40;//bmp information part 14-53 bits

    public static void main(String[] args)
    {
        String content=null;
        int count=0;
        try {
            int size=0;
            //Define a byte buffer, the size of the buffer is defined as needed
            byte[] buffer=new byte[1024];
            FileInputStream file = new FileInputStream("C:\\Users\\HL\\Desktop\\images\\image.bmp");
            //Loop to read the data in the file

            while((size=file.read(buffer))!=-1){
                count++;
                System.out.println("count="+count);
                for(int i=0;i<size;i++)
                {
                    System.out.println(i+" "+buffer[i]);
                }
            }
        //Close this file input stream and release all system resources related to this stream. 
        file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
