import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by phongpham on 4/27/15.
 */
public class SampleTest {

    public static void main(String[] args){
//        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            String input = null;
//            do{
//                System.out.print("Please enter your input:");
//                input = br.readLine();
//                System.out.println("You entered: " + input);
//            }while(input != null && !input.equalsIgnoreCase("q"));
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine());
        for(int i=2; i<100; i++){
            System.out.println("Is " + i + " a prime? " + isPrime(i));
        }
        List<Integer> integers = new ArrayList<Integer>();
        for(int i=0; i<15; i++){
            integers.add(i+1);
        }
        Random r = new Random();
        while(!integers.isEmpty()){
            int nextInt = r.nextInt();
            int idx = Math.abs(nextInt%integers.size());
            System.out.println("At idx[" + idx + "] with nextInt[" + nextInt + "], value is [" + integers.remove(idx) + "]");
        }
    }

    static int getNumberOfPrimes(int N) {
        int cnt = 0;
        for(int i=2; i<N; i++){
            if(isPrime(i)){
                cnt++;
            }
        }
        return cnt;
    }

    static boolean isPrime(int n){
        if(n != 2 && n%2 == 0){
            return false;
        }
        for(int i=3; i*i <= n; i+=2){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }

    static void missTerm(){
        Scanner sc = new Scanner(System.in);
        int cnt = Integer.valueOf(sc.nextLine());
        String str = sc.nextLine();
        String[] tmp = str.split(" ");
        int first = Integer.valueOf(tmp[0]);
        int last = Integer.valueOf(tmp[tmp.length - 1]);
        int inc = Math.abs(first-last)/cnt;
        for(int i=1; i<tmp.length-1; i++){
            int left = Integer.valueOf(tmp[i-1]);
            int right = Integer.valueOf(tmp[i]);
            int diff = Math.abs(left - right);
            if(diff != inc){
                System.out.println(left + inc * (left < right ? 1 : -1));
                break;
            }
        }
    }
}
