import java.io.IOException;

public class Network {

    public static void main(String[] args) throws IOException {
        boolean b=true;

        //Создание перцептрона (нейросети)
        Neural_network neutron = new Neural_network(784, 100, 10, 0.2);

        while(b){
        //получение команды из командной строки
        String[] cmd = Command.getCommandArgs();
        switch (cmd[0]){
            case "training":
                System.out.println("training...");
                //Выделение тренировочных данных mnist
                Data trainingData = new Data(Integer.parseInt(cmd[1]), cmd[2]);

                //тренировка сети
                for (int i = 0; i < trainingData.getLength(); i++) {
                    neutron.train(trainingData.getInput(i), trainingData.getTarget(i));
                }
                System.out.println("training complete!");
                break;
            case "test":
                Data test_data = new Data(Integer.parseInt(cmd[1]), cmd[2]);
                int statistic = 0;

                for (int i = 0; i < test_data.getLength(); i++) {
                    int num = get_number(neutron.run(test_data.getInput(i)));
                    System.out.println(num);
                    if (num == get_number(test_data.getTarget(i))) {
                        statistic++;
                    }
                }
                System.out.println(((double) statistic / (double) test_data.getLength())*100.0);
                break;
            case "image":
                double[][] data = Data.getData_fromPhoto(cmd[1]);
                double[][] d = neutron.run(data);
                Matr.show(d);
                int num = get_number(d);
                System.out.println(num);
                break;
            case "get_images":
                Testing.getImage_fromCSV(cmd[1]);
                break;
            case "exit":
                System.out.println("exiting...");
                b=false;
                break;
        }
        }

    }


    public static int get_number(double[][] Output){
        int maximum = 0;
        for (int i=1; i< Output.length; i++){
            if (Output[maximum][0]<Output[i][0]){
                maximum=i;
            }
        }
        return maximum;
    }
}
