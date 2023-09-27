import static java.lang.System.*;
public class Task02{
    public static void main(String[] args){
        long before = currentTimeMillis();
        
        divisorThread thr0 = new divisorThread(00001);
        divisorThread thr1 = new divisorThread(10001);
        divisorThread thr2 = new divisorThread(20001);
        divisorThread thr3 = new divisorThread(30001);
        divisorThread thr4 = new divisorThread(40001);
        divisorThread thr5 = new divisorThread(50001);
        divisorThread thr6 = new divisorThread(60001);
        divisorThread thr7 = new divisorThread(70001);
        divisorThread thr8 = new divisorThread(80001);
        divisorThread thr9 = new divisorThread(90001);
        thr0.start();
        thr1.start();
        thr2.start();
        thr3.start();
        thr4.start();
        thr5.start();
        thr6.start();
        thr7.start();
        thr8.start();
        thr9.start();
        try{
            thr0.join();
            thr1.join();
            thr2.join();
            thr3.join();
            thr4.join();
            thr5.join();
            thr6.join();
            thr7.join();
            thr8.join();
            thr9.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        int[] count = {thr0.maxCount, thr1.maxCount, thr2.maxCount, thr3.maxCount, thr4.maxCount, thr5.maxCount, thr6.maxCount, thr7.maxCount, thr8.maxCount, thr9.maxCount};
        int[] num = {thr0.maxNum, thr1.maxNum, thr2.maxNum, thr3.maxNum, thr4.maxNum, thr5.maxNum, thr6.maxNum, thr7.maxNum, thr8.maxNum, thr9.maxNum};
        int maxC = 0, maxN = 0;
        for(int i = 0; i < count.length; i++){
            if(count[i] > maxC){
                maxC = count[i]; maxN = num[i];
            }
        }
        
        out.printf("The number %d, with %d divisors, has the most number of divisors!%n", maxN, maxC);
        out.println("----------");
        long after = currentTimeMillis();
        out.printf("Time Elapsed(ms): %d%n", after-before);
        // time in ms for singlethread: 12181
        // time in ms for multithread: 2969
    }
}
class divisorThread extends Thread{
    int i, maxCount = 0, maxNum = 0;
    divisorThread(int i){
        this.i = i;
    }
    public void run(){
        for(int j = 0; j < 10000; j++){
            int tempCount = 0;
            for(int k = 1; k <= i; k++){
                if( i%k == 0){
                    tempCount++;
                }
            }
            if(tempCount > maxCount){
                maxCount = tempCount;
                maxNum = i;
            }
            i++;
        }
    }
}