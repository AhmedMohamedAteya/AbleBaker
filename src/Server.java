import java.util.Random;

public class Server {
    //public boolean busy = false;
        public int whenAvailable = 0;
        public static double[][] serviceProb = new double[4][2];
        Random rgen = new Random();
        public static int endTime;

        public Server(char server){
            if(server == 'a'){
                ableServiceTime();
            }
            else if(server == 'b'){
                bakerServiceTime();
            }
        }
        public static void ableServiceTime(){
            serviceProb[0][0] = 0.3;
            serviceProb[1][0] = 0.28;
            serviceProb[2][0] = 0.25;
            serviceProb[3][0] = 0.17;
            serviceProb[0][1] = serviceProb[0][0];
            serviceProb[1][1] = serviceProb[0][1]+serviceProb[1][0];
            serviceProb[2][1] = serviceProb[1][1]+serviceProb[2][0];
            serviceProb[3][1] = serviceProb[2][1]+serviceProb[3][0];
        }
        public static void bakerServiceTime(){
            serviceProb[0][0] = 0.35;
            serviceProb[1][0] = 0.25;
            serviceProb[2][0] = 0.2;
            serviceProb[3][0] = 0.2;
            serviceProb[0][1] = serviceProb[0][0];
            serviceProb[1][1] = serviceProb[0][1]+serviceProb[1][0];
            serviceProb[2][1] = serviceProb[1][1]+serviceProb[2][0];
            serviceProb[3][1] = serviceProb[2][1]+serviceProb[3][0];
        }
        public int serviceTime(char server){
            double rNumber = rgen.nextDouble();
            int tempTime=0;
            for(int i = 0 ; i <=3; i++){
                if(rNumber <= serviceProb[i][1]){
                    tempTime = i;
                    break;
                }
            }
            //calc real times
            int aTime = 0;
            if(server == 'a'){
                aTime = tempTime+2;
            }
            else if(server == 'b'){
                aTime = tempTime+3;
            }
            return aTime;
        }
}
