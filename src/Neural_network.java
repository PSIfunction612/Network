public class Neural_network {

    public double[][] weight_1;
    public double[][] weight_2;
    private double[][] in;
    private double[][] out;
    private double[][] layer;
    private double learninggrate;

    public Neural_network(int in, int inv, int out, double learninggrate){
        init(in, inv, out, learninggrate);
    }

    private void init(int in, int inv, int out, double learning){
        //инициализация сети по слоям и коэффициентам
        this.in = new double[in][1];
        this.layer = new double[inv][1];
        this.out = new double[out][1];

        this.learninggrate=learning;
        this.weight_1 = setRandom(inv,in);
        this.weight_2 = setRandom(out,inv);
    }

    public void train(double[][] input, double[][] target){
        //тренировка сети
        double[][] hidden_inputs = Matr.prod(weight_1, input);

        for (int i=0; i<this.layer.length; i++){
            this.layer[i][0]=activator(hidden_inputs[i][0]);
        }

        double[][] final_inputs = Matr.prod(weight_2, this.layer);

        for (int i =0; i< this.out.length; i++){
            this.out[i][0] = activator(final_inputs[i][0]);
        }

        double[][] output_errors = Matr.min(target, out);

        double[][] hidden_errors = Matr.prod(Matr.transponent(weight_2),output_errors);
        weight_2=Matr.sum(weight_2, Matr.dot(Matr.prod(Matr.mult(Matr.mult(out, output_errors),Matr.spec(out)),Matr.transponent(layer)), this.learninggrate));
        weight_1=Matr.sum(weight_1, Matr.dot(Matr.prod(Matr.mult(Matr.mult(layer, hidden_errors), Matr.spec(layer)),Matr.transponent(input)), this.learninggrate));
    }

    private double[][] setRandom(int i, int j){
        //установка случайных значений весовых коэффициентов
        double[][] W = new double[i][j];
        for (int k=0; k<W.length; k++){
            for (int l=0; l<W[0].length; l++){
                W[k][l]=Math.random()-0.5;
            }
        }
        return W;
    }

    public double[][] run(double[][] in){
        //прогон сети (получение значений выходных слоев)
        double[][] m = Matr.prod(weight_1, in);
        for (int i=0; i<this.layer.length; i++){
            this.layer[i][0]=activator(m[i][0]);
        }
        double[][] k = Matr.prod(weight_2, this.layer);
        for (int i =0; i< this.out.length; i++){
            this.out[i][0] = activator(k[i][0]);
        }
        return this.out;
    }

    private double activator(double x){
        //функция активации
        /*if (x>=0.5){
            return 1;
        }
        else {
            return 0;
        }*/
        return (1.0/(1.0+Math.exp(-1.0*x)));
    }

}
