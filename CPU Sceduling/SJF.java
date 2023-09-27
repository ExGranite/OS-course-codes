import static java.lang.System.*;
import java.util.*;
import java.io.*;
public class SJF {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("T1.txt"));
            // File contains number of processes in first line
            // and next lines contains arrival time and burst time
            int number = Integer.parseInt(sc.nextLine());
            int arrival_time[] = new int[number];
            int burst_time[] = new int[number];
            for (int i = 0; i < number; i++) {
                String read[] = sc.nextLine().split(" ");
                arrival_time[i] = Integer.parseInt(read[0]);
                burst_time[i] = Integer.parseInt(read[1]);
            }

            int finish_time[] = new int[number];
            int turnaround_time[] = new int[number];
            int waiting_time[] = new int[number];
            boolean process_status[] = new boolean[number];
            
            int process_time[] = new int[number];
            for (int i = 0; i < number; i++) {
                process_time[i] = burst_time[i];
            }
    
            int time = 0, completed = 0;
            while (completed != number) {
    
                int min_burst = 99999 , process = -1;
                for ( int i = 0; i < number; i++) {
                    if ((arrival_time[i] <= time) && (process_time[i] < min_burst) && (!process_status[i])) {
                        min_burst = process_time[i];
                        process = i;
                    }
                }
               
                if (process == -1) {
                    time++;
                } else {
                    process_time[process]--;
                    time++;
                }
    
                if (process_time[process] == 0) {
                    finish_time[process] = time;
                    process_status[process] = true;
                }
    
                completed = 0;
                for (int i = 0; i < number; i++) {
                    if (process_status[i]) {
                        completed++;
                    }
                }
            }
            
            for( int i = 0; i < number; i++) {
                turnaround_time[i] = finish_time[i] - arrival_time[i];
                waiting_time[i] = turnaround_time[i] - burst_time[i];
            }
    
            for(int i = 0; i < number; i++) {
                out.printf("Process %d:%n", i+1);
                out.printf("\tCompletion Time (CT): %d%n", finish_time[i]);
                out.printf("\tTurnaround Time (TAT): %d%n", turnaround_time[i]);
                out.printf("\tWaiting Time (WT): %d%n", waiting_time[i]);
            }
            out.println();
            int t_sum = 0, w_sum = 0;
            for (int i = 0; i < number; i++){
                t_sum += turnaround_time[i];
                w_sum += waiting_time[i];
            }
            double t_avg = t_sum/number;
            double w_avg = w_sum/number;
            out.printf("Average Turnaround Time: %.2f%n", t_avg);
            out.printf("Average Waiting Time: %.2f%n", w_avg);

            sc.close();
        } catch (Exception e) {
            err.println(e);
        }
    }
}