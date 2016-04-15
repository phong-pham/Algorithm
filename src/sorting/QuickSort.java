package sorting;

/**
 * Created by phongpham on 5/3/15.
 *
 * Time Complexity
 * Best: O(nlog(n))
 * Average: O(nlog(n))
 * Worst: O(n*n)
 *
 * Space Complexity
 * Worst:  O(log(n))
 *
 */
public class QuickSort extends Sorting{

    public static void main(String[] args){

        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        System.out.println("original arr: ");
        printArr(input);
        quickSort(input);

        input = new int[]{11, 14, 16, 12, 11, 15};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        quickSort(input);

        input = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        quickSort(input);

        input = new int[]{6, 5, 4, 3, 2, 1};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        quickSort(input);

        input = new int[]{1, 12, 5, 26, 7, 14, 3, 7, 2};
        System.out.println("\n\noriginal arr: ");
        printArr(input);
        quickSort(input);
    }

    public static void quickSort(int[] arr){

        swapCnt = 0;
        iterationCnt = 0;
        if(arr.length > 1){
            doQuickSort(arr, 0, arr.length - 1);
        }

        printArr(arr);
        System.out.println("\n\nNumber of swap[" + swapCnt + "], and iteration[" + iterationCnt + "] for the list of " + arr.length + " elements");
    }

    public static void doQuickSort(int[] arr, int low, int high){
        if(low >= high){
            return;
        }
        int middle = low + (high-low)/2;
        int pivot = arr[middle];
        System.out.println("pivot value: " + pivot);
        int i = low, j = high;
        while(i <= j){
            while(arr[i] < pivot){
                iterationCnt++;
                i++;
            }
            while(arr[j]> pivot){
                iterationCnt++;
                j--;
            }
            if(i <= j){
                if(arr[i] != arr[j]){
                    swapValue(arr, i, j);
                }

                i++;
                j--;
            }
        }
        if(low < (i-1))
            doQuickSort(arr, low, i-1);
        if(i < high)
            doQuickSort(arr, i, high);

    }
}
