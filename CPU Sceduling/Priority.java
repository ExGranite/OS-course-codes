import static java.lang.System.*;
import java.util.*;
import java.io.*;
public class Priority {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("T2.txt"));
            // File contains number of processes in first line
            // and next lines contains burst time and priority
            int number = Integer.parseInt(sc.nextLine());
            int burst_time[] = new int[number];
            int priority[] = new int[number];
            for (int i = 0; i < number; i++) {
                String read[] = sc.nextLine().split(" ");
                burst_time[i] = Integer.parseInt(read[0]);
                priority[i] = Integer.parseInt(read[1]);
            }

            int arrival_time[] = new int[number];
            int finish_time[] = new int[number];
            int turnaround_time[] = new int[number];
            int waiting_time[] = new int[number];
            boolean process_status[] = new boolean[number];
    
            int time = 0, completed = 0;
            while (completed != number) {
    
                int min_priority = 99999 , process = -1;
                for ( int i = 0; i < number; i++) {
                    if ((priority[i] < min_priority) && (!process_status[i])) {
                        min_priority = priority[i];
                        process = i;
                    }
                }
               
                time += burst_time[process];
                finish_time[process] = time;
                process_status[process] = true;
                completed++;
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