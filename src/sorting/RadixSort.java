package sorting;

/**
 * Created by phongpham on 3/27/16.
 */
public class RadixSort extends Sorting{

    public static void main(String[] args){
        int[] input = {24,2,45,20,56,75,2,56,99999,53,12};
        System.out.println("original arr: ");
        printArr(input);
        radixSort(input);
        printArr(input);
    }

    public static void radixSort(int[] a){
        int i, m = a[0], exp = 1, n = a.length;
        int[] b = new int[n];
        int[] bucket = new int[10];
        int count = 0;
        for (i = 1; i < n; i++){
            if (a[i] > m){
                m = a[i];
            }
        }
        while (m / exp > 0){
            count++;
            System.out.println("At iteration " + count);
            for(i=0; i<10; i++){
                bucket[i] = 0;
            }

            for (i = 0; i < n; i++){
                bucket[(a[i] / exp) % 10]++;
            }
            System.out.println("\n\nFirst");
            printArr(bucket);
            for (i = 1; i < 10; i++){
                bucket[i] += bucket[i - 1];
            }
            System.out.println("Second");
            printArr(bucket);
            for (i = n - 1; i >= 0; i--){
                int bIdx = --bucket[(a[i] / exp) % 10];
                System.out.println(i + "/value is [" + a[i] + "], bucket idx[" + ((a[i] / exp) % 10) + "], b idx[" + bIdx + "]" );
                b[bIdx] = a[i];
            }
            for (i = 0; i < n; i++){
                a[i] = b[i];
            }
            System.out.println("At exp[" + exp + "], array is");
            printArr(a);
            exp *= 10;
        }
    }
}
