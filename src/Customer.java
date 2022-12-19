import java.util.Random;

public class Customer {
    public int arrivalTime;
    public int serviceStartTime;
    public int serviceTime;
    public int customerNum;
    public int endTime;
    public static double[][] interarrivalProb = new double[5][2];
    Random rgen = new Random();

    public Customer(){
        interarrivalTable();
        double rTempOne = rgen.nextDouble();
        for(int i = 1 ; i <=4; i++){
            if(rTempOne <= interarrivalProb[i][1]){
                arrivalTime = i;
                break;
            }
        }
    }

    public static void interarrivalTable(){
        interarrivalProb[1][0] = 0.25;
        interarrivalProb[2][0] = 0.4;
        interarrivalProb[3][0] = 0.2;
        interarrivalProb[4][0] = 0.15;
        interarrivalProb[1][1] = interarrivalProb[1][0];
        interarrivalProb[2][1] = interarrivalProb[1][1]+interarrivalProb[2][0];
        interarrivalProb[3][1] = interarrivalProb[2][1]+interarrivalProb[3][0];
        interarrivalProb[4][1] = interarrivalProb[3][1]+interarrivalProb[4][0];
    }

}
