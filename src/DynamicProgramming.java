/**
 * Created by phongpham on 5/18/15.
 */
public class DynamicProgramming {

    public static void main(String[] args){
        int[] arr = {1, 7, 4, 9, 2, 5};
//        System.out.println("doZigZag for arr.length=" + arr.length);
//        printArr(arr);
//        System.out.println("result is: " + doZigZag(arr) + "\n\n");

        arr= new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println("doZigZag for arr.length=" + arr.length);
        printArr(arr);
        System.out.println("result is: " + doZigZag(arr) + "\n\n");

//        arr= new int[]{44};
//        System.out.println("doZigZag for arr.length=" + arr.length);
//        printArr(arr);
//        System.out.println("result is: " + doZigZag(arr) + "\n\n");

//        arr= new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println("doZigZag for arr.length=" + arr.length);
//        printArr(arr);
//        System.out.println("result is: " + doZigZag(arr) + "\n\n");

        arr= new int[]{70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32};
        System.out.println("doZigZag for arr.length=" + arr.length);
        printArr(arr);
        System.out.println("result is: " + doZigZag(arr) + "\n\n");

        arr= new int[]{374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244};
        System.out.println("doZigZag for arr.length=" + arr.length);
        printArr(arr);
        System.out.println("result is: " + doZigZag(arr) + "\n\n");

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        buildMatrix(arr);

    }

    public static int doZigZag(int[] arr){
        int result = 0;
        int count = 2;

        if(arr.length <= 2){
            return arr.length;
        }
        int[][]dp = new int[2][arr.length];
        for(int i=0; i<arr.length; i++){
            dp[0][i] = dp[1][i] = 1;
            for(int j=0; j<i; j++){
//                System.out.println("comparing " + arr[i] + " with " + arr[j]);
                if((arr[i] - arr[j]) > 0){
                    dp[0][i] = Math.max(dp[1][j] + 1, dp[0][i]);
                }else if(arr[i] - arr[j] < 0){
                    dp[1][i] = Math.max(dp[0][j] + 1, dp[1][i]);
                }
            }
            result = Math.max(dp[0][i], Math.max(dp[1][i], result));
        }
        printMatrix(dp);
        int[] sequence = new int[result];
        count = 2;
        sequence[0] = arr[0];
        int tmp = 1;
        for(int i=1; i<arr.length; i++){
            if(count == 2){
                boolean found2 = false;
                if(dp[0][i] == 2){
                    found2 = true;
                    tmp = 0;
                }else if(dp[1][i] == 2){
                    found2 = true;
                    tmp = 1;
                }
                if(found2){
                    sequence[count-1] = arr[i];
                    count++;
                    tmp = tmp == 0 ? 1 : 0;
                }

            }else if(dp[tmp][i] == count){
                sequence[count-1] = arr[i];
                count++;
                tmp = tmp == 0 ? 1 : 0;
            }
        }
        printArr(sequence);
        return result;
    }

    public static void getCoins(int[] options, int total){

    }

    public static void printArr(int[] arr){
        String str = "";
        for(int i=0; i<arr.length; i++){
            str += arr[i] + " ";
        }
        System.out.println(str.trim());
    }
    public static void printMatrix(int[][] m){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m[i].length; j++){
                sb.append(m[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int[][] buildMatrix(int[] input){
        int len = input.length;
        int[][] result = new int[len][len];
        for(int i=0; i<len; i++){
            for(int j=0; j<len-i; j++){
                result[i][j] = input[i+j];
            }
        }
        printMatrix(result);
        return result;
    }
}
