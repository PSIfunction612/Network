//Получение изображения и раскодировка в формат MNIST
//Написано Шалагиным Максимом 25.11.2019
//

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Data {

    private double[][] data;

    public Data(int lines, String name){
        data = getData_CSV(lines, name);
    }

    public int getLength(){
        return  data[0].length;
    }

    public static double[][] getData_CSV(int lines, String name){

        double[][] Data = new double[785][lines];

        try {
        BufferedReader reader = new BufferedReader(new FileReader(name));
        String line = null;
        int index = 0;


        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Data[0][index]=Double.parseDouble(data[0]);
            for (int i=1; i < data.length;i++){
                Data[i][index]=(Double.parseDouble(data[i])/255.0*0.99)+0.01;
            }
            index++;
        }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return Data;
    }

    public static double[][] getData_fromPhoto(String name) {
        double[][] data = new double[784][1];
        try {
            File file = new File(name);
            BufferedImage source = ImageIO.read(file);
            int i=0;

            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    Color color = new Color(source.getRGB(x, y));

                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();

                    int col = (blue+red+green)/3;
                    data[i][0]=(255-col);
                    //data[i][0]=((255-col)/255.0*0.99)+0.01;

                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден или не удалось сохранить");
        }
        Matr.show(data);
        return data;
    }

    public double[][] getTarget(int st){

        int number = (int)this.data[0][st];
        double[][] target_matrix = new double[10][1];

        for (int i=0; i<target_matrix.length; i++){
            target_matrix[i][0]=0.01;
        }
        target_matrix[number][0]=0.99;
        return target_matrix;
    }

    public double[][] getInput(int st){
        double[][] Input = new double[this.data.length-1][1];
        for(int i=0; i<Input.length; i++){
            Input[i][0]=this.data[i+1][st];
        }
        return Input;
    }

}
