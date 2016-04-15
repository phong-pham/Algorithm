/**
 * Created by phongpham on 12/29/15.
 */
public class Facebook_Combination {

    public static int count = 1;

    public static void main(String[] args){
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int checkSum = 100;
        boolean printCombo = true;
        int[][] matrix = buildMatrix(input);
        printMatrix(matrix);
        /*
            Build matrix such as
            1 2 3
            2 3 0
            3 0 0

            -start with the first row
            -recursively jump to the next possible row based on the number of digit created on checking row
                (so that digit will not be reused in one combination)

         */
        String str = "";
        for(int i=0; i<matrix.length; i++){
            str += matrix[0][i];
            //build combination starting with positive number
            buildCombo(str, i+1, matrix, checkSum, printCombo);
            //build combination starting with negative number
            buildCombo("-" + str, i+1, matrix, checkSum, printCombo);
        }
    }

    /**
     *
     * @param prevStr accumulated numbers from previous steps
     * @param row checking row
     * @param matrix the matrix of digit
     * @param checkSum value to validate combination with
     * @param printCombo true to print out all possible combination, do not check with checkSum
     *
    */
    public static void buildCombo(String prevStr, int row, int[][]matrix, int checkSum, boolean printCombo){
        if(row < matrix.length){
            int[] rowValues = matrix[row];
            String str = "";
            for(int i=0; i<rowValues.length; i++){
                if(rowValues[i] != 0){
                    str += rowValues[i];
                    buildCombo(prevStr + "," + str, row+i+1, matrix, checkSum, printCombo);
                    buildCombo(prevStr + ",-" + str, row+i+1, matrix, checkSum, printCombo);
                }else{
                    break;
                }
            }
        }else{
            if(printCombo){
                System.out.println(count + "/ " + prevStr);
                count++;
            }else{
                String[] tmp = prevStr.split(",");
                int check = 0;
                for(int i=0; i<tmp.length; i++){
                    check += Integer.valueOf(tmp[i]);
                }
                if(check == checkSum){
                    System.out.println(count + "/ " + prevStr);
                    count++;
                }
            }
        }

    }

    public static int[][] buildMatrix(int[] input){
        int len = input.length;
        int[][] result = new int[len][len];
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(j < (len-i)){
                    result[i][j] = input[i+j];
                }else{
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    public static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
            if(i<arr.length-1){
                System.out.print(",");
            }
        }
    }

    public static void printMatrix(int[][] matrix){
        String separator = "";
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
            separator += "--";
        }
        System.out.println(separator);
    }
}
