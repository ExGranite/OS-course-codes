import static java.lang.System.*;
import java.util.*;
public class Task03{
    public static void main(String args[]){
        Scanner sc = new Scanner(in);
        out.print("Enter length of array: ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            out.print("Enter element: ");
            arr[i] = sc.nextInt();
        }
        
        MergeSortThread thr = new MergeSortThread(arr, 0, arr.length-1);
        out.print("Original Array: ");
        thr.printArray(arr);
        
        thr.start();
        try{
            thr.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        out.print("Sorted Array: ");
        thr.printArray(arr);
        sc.close();
    }
}
class MergeSortThread extends Thread{
    int[] arr;
    int l, r;
    MergeSortThread(int[] a, int l, int r){
        this.arr = a; this.l = l; this.r = r;
    }
    
    public void run(){
        if(l < r){
            int m = l + (r-l)/2;
            // Separating the array into two
            MergeSortThread thr1 = new MergeSortThread(arr, l, m);
            MergeSortThread thr2 = new MergeSortThread(arr, m+1, r);
            thr1.start(); thr2.start();
            try{
                thr1.join(); thr2.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
            // Merging the two arrays
            int left[] = new int[m - l + 1];
            int right[] = new int[r - m];
            
            for(int i = 0; i < left.length; i++){
                left[i] = arr[l + i];
            }
            for(int j = 0; j < right.length; j++){
                right[j] = arr[m + 1 + j];
            }
            
            int i = 0, j = 0, k = l;
            while(i < left.length && j < right.length){
                if(left[i] <= right[j]){
                    arr[k] = left[i];
                    i++;
                }
                else{
                    arr[k] = right[j];
                    j++;
                }
                k++;
            }
            while(i < left.length){
                arr[k] = left[i];
                i++; k++;
            }
            while(j < right.length){
                arr[k] = right[j];
                j++; k++;
            }
        }
    }
    
    void printArray(int arr[]){
        for (int i = 0; i < arr.length; i++){
            out.printf("%d ", arr[i]);
        }
        out.println();
    }
}