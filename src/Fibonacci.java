/**
 * Created by phongpham on 5/21/15.
 */
public class Fibonacci {

    public static void main(String[] args){
        System.out.println("Fibonacci sequence with length 5:");
        System.out.println("Recursive: " + doFibonacciRecursive(5));
        System.out.println("Loop: " + doFibonacciLoop(5));

        System.out.println("\n\nFibonacci sequence with length 10:");
        System.out.println("Recursive: " + doFibonacciRecursive(10));
        System.out.println("Loop: " + doFibonacciLoop(10));

        System.out.println("\n\nFibonacci sequence with length 50:");
//        System.out.println("Recursive: " + doFibonacciRecursive(50));
        System.out.println("Loop: " + doFibonacciLoop(50));
    }

    public static long doFibonacciRecursive(long n){
        return n <= 1 ? n : doFibonacciRecursive(n-1) + doFibonacciRecursive(n-2);
    }

    public static long doFibonacciLoop(int n){
        long result = 0, next = 0, previous = 1;
        long[] seq = new long[n+1];
        for(int i=1; i<=n; i++){
            result = next + previous;
            previous = next;
            next = result;
            seq[i] = result;
        }
        printArr(seq);
        return result;
    }

    public static void printArr(long[] arr){
        String str = "";
        for(int i=0; i<arr.length; i++){
            str += arr[i] + " ";
        }
        System.out.println(str.trim());
    }
}
