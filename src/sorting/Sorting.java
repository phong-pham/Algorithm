package sorting;

/**
 * Created by phongpham on 5/4/15.
 */
public class Sorting {

    public static int swapCnt = 0;

    public static int iterationCnt = 0;

    public static void swapValue(int[] arr, int srcIdx, int destIdx){
        swapCnt++;
        System.out.println("about to swap [" + arr[srcIdx] + "] with [" + arr[destIdx] + "] for " + swapCnt + " times.");

        int tmp = arr[destIdx];
        arr[destIdx] = arr[srcIdx];
        arr[srcIdx] = tmp;
    }

    public static void printArr(int[] arr){
        String str = "";
        for(int i=0; i<arr.length; i++){
            str += arr[i] + " ";
        }
        System.out.println(str.trim());
    }
}
