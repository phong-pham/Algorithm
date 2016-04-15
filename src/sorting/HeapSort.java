package sorting;

/**
 * Created by phongpham on 5/5/15.
 */
public class HeapSort extends Sorting {

    private static int length = 0;
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
        if(arr.length > 0){
            swapCnt = 0;
            doHeapSort(arr);
        }
        System.out.println("Result: ");
        printArr(arr);
    }

    public static void doHeapSort(int[] arr){
        buildHeap(arr);
        System.out.println("Heap:::");
        printArr(arr);
        for(int i=length; i>=0; i--){
            swapValue(arr, i, 0);
            length--;
            maxHeap(arr, 0);
//            printArr(arr);
        }
    }

    public static void buildHeap(int[]arr){
        length = arr.length - 1;
        for(int i=arr.length/2; i >= 0; i--){
            maxHeap(arr, i);
        }
    }

    public static void maxHeap(int[]arr, int i){
        int left = 2*i;
        int right = 2*i + 1;
        int max = i;
        if(left <= length && arr[left] < arr[max]){
            max = left;
        }
        if(right <= length && arr[right] < arr[max]){
            max = right;
        }
        if(max != i){
            swapValue(arr, max, i);
//            maxHeap(arr, max);
        }
    }
}
