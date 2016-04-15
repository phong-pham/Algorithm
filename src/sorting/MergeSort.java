package sorting;

/**
 * Created by phongpham on 5/4/15.
 *
 * Time Complexity
 * Best: O(nlog(n))
 * Average: O(nlog(n))
 * Worst: O(nlog(n))
 *
 * Space Complexity
 * Worst: O(n)
 *
 */
public class MergeSort extends Sorting{

    public static int[]tempArr;

    public static void main(String[] args){

        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        mergeSort(input, false);
        mergeSort(input, true);
        input = new int[]{11, 14, 16, 12, 11, 15};
        mergeSort(input, false);
        mergeSort(input, true);
    }

    public static void mergeSort(int[] arr, boolean reverse){
        if(arr.length > 1){
            tempArr = new int[arr.length];
            doMergeSort(arr, 0, arr.length - 1, reverse);
        }
        printArr(arr);
    }

    public static void doMergeSort(int[] arr, int low, int high, boolean reverse){
        if(low < high){
            int middle = low + (high - low)/2;
            doMergeSort(arr, low, middle, reverse);
            doMergeSort(arr, middle + 1, high, reverse);
            mergePars(arr, low, middle, high, reverse);
        }
    }

    public static void mergePars(int[] arr, int low, int middle, int high, boolean reverse){
        int[]temp = new int[arr.length];
        for(int i=low; i<=high; i++){
            temp[i] = arr[i];
        }
        int i = low,
            j = middle + 1,
            k = low;
        while(i <= middle && j <= high){
            if((reverse && temp[i] > temp[j])
                || (!reverse && temp[i] <= temp[j])){
                arr[k] = temp[i];
                i++;
            }else{
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while(i <= middle){
            arr[k] = temp[i];
            i++;
            k++;
        }
    }
}
