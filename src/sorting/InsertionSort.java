package sorting;

/**
 * Created by phongpham on 5/5/15.
 */
public class InsertionSort extends Sorting{

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
            doInsertionSortWithSwap(arr);
        }
        printArr(arr);
    }

    public static void doInsertionSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            int val = arr[i];
            int j = i;
            while(j > 0 && arr[j-1] > val){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = val;
        }
    }

    public static void doInsertionSortWithSwap(int[] arr){
        for(int i=0; i<arr.length; i++){
            int j=i;
            while(j > 0 && arr[j-1] > arr[j]){
                swapValue(arr, j-1, j);
                j--;
            }
        }
    }

}
