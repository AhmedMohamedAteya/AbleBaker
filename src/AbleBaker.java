import java.util.LinkedList;
import java.util.Queue;

public class AbleBaker {
    public static Server Able = new Server('a');
    public static Server Baker = new Server('b');
    public static Customer[] Customers = new Customer[10];
    public static int cumulativeInterarrival = 0;
    public static String server;
    public static int queueLength=0;
    public static Queue<Customer> customerQueue = new LinkedList<Customer>();

    public static void main(String[] args) {
        Customers[0] = new Customer();
        Customers[0].arrivalTime = 0;
        Customers[0].serviceStartTime = 0;
        Customers[0].serviceTime = Able.serviceTime('a');
        server = "      Able";
        Customers[0].endTime = Customers[0].serviceTime;
        // Able.busy = true;
        Able.whenAvailable = Customers[0].serviceTime;

        System.out.println("Cust    Inter   Cummu   Able    Baker   Serve   Start   Server  EndTime");
        System.out.println(1 + "        " +Customers[0].arrivalTime+ "      "+
                cumulativeInterarrival+"        "+ Able.whenAvailable+ "        " +
                Baker.whenAvailable+"       "+Customers[0].serviceTime+
                "       "+Customers[0].serviceStartTime + server + "        "+ Customers[0].endTime);
        for(int i = 1; i < 10; i++){
            Customers[i]= new Customer();
            int tempI=i;

            cumulativeInterarrival += Customers[i].arrivalTime;
            update();

            //If Able frees up
            if(Able.whenAvailable <= Baker.whenAvailable
                    && Able.whenAvailable <= cumulativeInterarrival){

                Able.whenAvailable = Able.whenAvailable + Customers[i].serviceTime;

                Customers[i].serviceStartTime = cumulativeInterarrival;
                Customers[i].serviceTime = Able.serviceTime('a');
                Able.whenAvailable = cumulativeInterarrival
                        + Customers[i].serviceTime;
                Customers[i].endTime = Customers[i].serviceStartTime + Customers[i].serviceTime;

                //If Baker frees up
            } else if (Baker.whenAvailable <= cumulativeInterarrival){
                Baker.whenAvailable = Baker.whenAvailable + Customers[i].serviceTime;
                Customers[i].serviceStartTime = cumulativeInterarrival;
                Customers[i].serviceTime = Baker.serviceTime('b');
                Baker.whenAvailable = cumulativeInterarrival
                        + Customers[i].serviceTime;
                Customers[i].endTime = Customers[i].serviceStartTime + Customers[i].serviceTime;
                //If neither free up, add them to the Queue
            } else {
                customerQueue.add(Customers[i]);
                queueLength ++;
                i--;
            }

            if( Customers[i].serviceTime == Able.serviceTime('a'))
            {
                server = "Able";
            }
            else
            {
                server = "Baker";
            }

            if(tempI == i){
                System.out.println(i + 1 + "        " +Customers[i].arrivalTime+"       "+
                        cumulativeInterarrival+"        "+Able.whenAvailable+"      "+
                        Baker.whenAvailable+"       "+Customers[i].serviceTime+"        "+
                        Customers[i].serviceStartTime + "       " + server +
                        "       " + Customers[i].endTime);
            }
        }
    }
    //-----------------------------------------------------------------------------
//update()
    public static void update(){
        Customer newCustomer;
        while(!customerQueue.isEmpty()){
            if(Able.whenAvailable <= Baker.whenAvailable
                    && Able.whenAvailable <= cumulativeInterarrival){
                newCustomer = customerQueue.remove();
                queueLength--;
                newCustomer.serviceTime = Able.serviceTime('a');
                Able.whenAvailable = Able.whenAvailable + newCustomer.serviceTime;
            } else if (Baker.whenAvailable <= cumulativeInterarrival){
                newCustomer = customerQueue.remove();
                queueLength--;
                newCustomer.serviceTime = Baker.serviceTime('b');
                Baker.whenAvailable = Baker.whenAvailable + newCustomer.serviceTime;
            } else {
                break;
            }
        }
    }
    
}
