package sorting;

/**
 * Created by phongpham on 5/5/15.
 */
public class SelectionSort extends Sorting {

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
            doSelectionSort(arr);
        }
        printArr(arr);
    }

    public static void doSelectionSort(int[] arr){
        for(int i=0; i<arr.length - 1; i++){
            int minIdx = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[minIdx]){
                    minIdx = j;
                }
            }
            if(minIdx != i){
                swapValue(arr, minIdx, i);
            }
        }
    }
}
