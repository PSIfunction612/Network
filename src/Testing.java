import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Testing {
    public static void getImage_fromCSV(String name){

        BufferedImage img = new BufferedImage(28,28, BufferedImage.TYPE_INT_RGB);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            String line = null;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                for (int i=1; i < data.length;i++){
                    int col = 255-Integer.parseInt(data[i]);
                    int x = (i-1)%28;
                    int y = (i-1)/28;
                    Color clr = new Color(col,col,col);
                    img.setRGB(x,y,clr.getRGB());
                }
                String Name = "data"+Integer.toString(index)+".png";
                File output = new File(Name);
                ImageIO.write(img, "png", output);
                System.out.println("Изображение сохранено");
                index++;
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
