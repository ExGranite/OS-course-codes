import static java.lang.System.*;
public class Task01{
    public static void main(String[] args){
        printThread thr1 = new printThread();
        thr1.start();
    }
}
class printThread extends Thread{
    static int i = 1;
    public void run(){
        int j = 0;
        while(j < 10){
            out.printf("%d ", i);
            j++; i++;
        }
        out.println();
//        out.println(getName());
        if((i == 11)){
            printThread thr2 = new printThread();
            thr2.start();
            try{
                thr2.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            j = 0;
            while(j < 10){
                out.printf("%d ", i);
                j++; i++;
            }
            out.println();
//            out.println(getName());
        }
    }
}