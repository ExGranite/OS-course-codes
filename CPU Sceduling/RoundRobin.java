import static java.lang.System.*;
import java.util.*;
import java.io.*;
public class RoundRobin {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("T3.txt"));
            // File contains number of processes in first line
            // and next lines contains burst time
            //  amd last line contains quantum time
            int number = Integer.parseInt(sc.nextLine());
            int burst_time[] = new int[number];
            for (int i = 0; i < number; i++) {
                burst_time[i] = Integer.parseInt(sc.nextLine());
            }
            int quantum = Integer.parseInt(sc.nextLine());
            
            int arrival_time[] = new int[number];
            int finish_time[] = new int[number];
            int turnaround_time[] = new int[number];
            int waiting_time[] = new int[number];
            
            int process_time[] = new int[number];
            Queue q = new Queue();
            for (int i = 0; i < number; i++) {
                process_time[i] = burst_time[i];
                q.enqueue(i);
            }
            
            int time = 0;
            while (q.check()) {
                int process = q.dequeue();

                if (process_time[process] > quantum) {
                    process_time[process] -= quantum;
                    time += quantum;
                } else {
                    time += process_time[process];
                    process_time[process] = 0;
                }
                
                if (process_time[process] == 0) {
                    finish_time[process] = time;
                } else {
                    q.enqueue(process);
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
            // e.printStackTrace();
        }
    }
}

class Queue {
    Node head;
    Node tail;
    
    public void enqueue(int a) {
        Node n = new Node(a);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        // out.println(a + " Enqueued");
    }

    public boolean check() {
        if (head == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public int dequeue() {
        Node temp = head;
        head = head.next;
        // out.println(temp.position + " Dequeued");
        return temp.position;
    }
}

class Node {
    Node next;
    int position;
    Node(int p) {
        position = p;
    }
}