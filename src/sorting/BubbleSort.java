package sorting;

import sorting.Sorting;

/**
 * Created by phongpham on 5/5/15.
 */
public class BubbleSort extends Sorting {

    public static void main(String[] args){

        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        System.out.println("original arr: ");
        printArr(input);
        doSort(input);

        input = new int[]{11, 14, 16, 12, 11, 15};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        doSort(input);

        input = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        doSort(input);

        input = new int[]{6, 5, 4, 3, 2, 1};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        doSort(input);

        input = new int[]{1, 12, 5, 26, 7, 14, 3, 7, 2};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        doSort(input);
    }

    public static void doSort(int[] arr){
        if(arr.length > 1){
            swapCnt = 0;
            doBubbleSort(arr);
        }
        printArr(arr);
    }

    public static void doBubbleSort(int[] arr){
        boolean swapped = true;
        int idx = 0;
        while(swapped){
            swapped = false;
            idx++;
            for(int i=0; i<arr.length-idx; i++){
                if(arr[i] > arr[i+1]){
                    swapValue(arr, i, i+1);
                    swapped = true;
                }
            }
        }
    }
}
