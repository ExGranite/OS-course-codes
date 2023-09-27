import static java.lang.System.*;
import java.util.*;
import java.io.*;
public class BankersAlgorithm{
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("A:\\SUMMER 2021\\CSE321\\Lab\\6\\ok.txt"));
            int row = Integer.parseInt(sc.nextLine());
            int column = Integer.parseInt(sc.nextLine());
            char[] process = new char[row];
            int[][] max = new int[row][column];
            int[][] allocation = new int[row][column];
            int[][] need = new int[row][column];
            int[][] available = new int[row+1][column];
            for (int i = 0; i < row; i++) {
                String[] s = sc.nextLine().split(" ");
                for (int j = 0; j < s.length; j++) {
                    max[i][j] = Integer.parseInt(s[j]);
                }
            }
            for (int i = 0; i < row; i++) {
                String[] s = sc.nextLine().split(" ");
                for (int j = 0; j < s.length; j++) {
                    allocation[i][j] = Integer.parseInt(s[j]);
                    need[i][j] = max[i][j] - allocation[i][j];
                }
            }
            out.printf("Number of Processes: %d%n", row);
            out.printf("Number of Resources: %d%n", column);
            out.print("Process(s): ");
            int c = 65;
            for (int i = 0; i < process.length; i++) {
                process[i] = (char)c;
                out.printf("%s ", process[i]);
                c++;
            }
            out.println();
            out.println("Max Resource Matrix:");
            for (int i = 0; i < row; i++) {
                String s = "";
                for (int j = 0; j < column; j++) {
                    s += max[i][j] + " ";
                }
                out.printf("\t%s%n", s);
            }
            out.println("Resource Allocation Matrix:");
            for (int i = 0; i < row; i++) {
                String s = "";
                for (int j = 0; j < column; j++) {
                    s += allocation[i][j] + " ";
                }
                out.printf("\t%s%n", s);
            }
            out.println("Need Matrix:");
            for (int i = 0; i < row; i++) {
                String s = "";
                for (int j = 0; j < column; j++) {
                    s += need[i][j] + " ";
                }
                out.printf("\t%s%n", s);
            }
            out.print("Available Resources: ");
            String[] a = sc.nextLine().split(" ");
            for (int i = 0; i < a.length; i++) {
                available[0][i] = Integer.parseInt(a[i]);
                out.printf("%d ", available[0][i]);
            }
            LinkedList<Integer> sequence = new LinkedList<Integer>();
            int counter = 0;
            for (int i = 0; ; i++) {
                i %= row;
                boolean flag = true;
                for (int j = 0; j < column; j++) {
                    if (need[i][j] <= available[counter][j]) {
                        if (flag && j == (column-1) && !sequence.contains(i)) {
                            for (int k = 0; k < column; k++) {
                                available[counter+1][k] = available[counter][k] + allocation[i][k];
                            }
                            sequence.addLast(i);
                            // i = -1; // for assignment
                            counter++;
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (sequence.size() == row) {
                    break;
                }
            }
            out.println();
            out.print("Safe Sequence: ");
            for (int i = 0; i < sequence.size(); i++) {
                out.printf("%s ", process[sequence.get(i)]);
            }
            out.println();
            out.println("Change in Available Resource Matrix:");
            for (int i = 0; i < available.length; i++) {
                String s = "";
                for (int j = 0; j < column; j++) {
                    s += available[i][j] + " ";
                }
                out.printf("\t%s%n", s);
            }
        } catch (Exception a){
            err.println(a);
            // a.printStackTrace();
        }
    }
}