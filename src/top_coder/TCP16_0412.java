package top_coder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongpham on 4/13/16.
 */
public class TCP16_0412 {

    public static void main(String[] args){
        runExploringNumbers();
        runGetMaxStockWorth();
    }

    public static void runExploringNumbers(){
        System.out.println("\n\n**********Running Exploring Numbers**********\n");

        boolean printSequence = false;

        /*expected 1*/
        int n = 5;
        int exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");

        /*expected 4*/
        n = 57;
        exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");

        /*expected -1*/
        n = 1;
        exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");

        /*expected 2*/
        n = 6498501;
        exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");

        /*expected 6*/
        n = 989113;
        exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");

        /*expected -1*/
        n = 12366;
        exploringNumbersResult = exploringNumbers(n, printSequence);
        System.out.println("exploring number with n[" + n + "]: " + exploringNumbersResult + "\n");
    }

    public static int exploringNumbers(int n, boolean printSequence){
        List<Integer> sequences = new ArrayList<Integer>();
        int number = n;
        if(isPrime(n)){
            sequences.add(n);
        }
        boolean bored = sequences.size() == n;
        while(!isPrime(number) && !bored){
            if(sequences.size() == n){
                bored = true;
                break;
            }
            sequences.add(number);
            int temp = number;
            int mod;
            number = 0;
            while(temp > 0){
                mod = (temp%10);
                temp = temp/10;
                number += mod*mod;
            }
            if(isPrime(number)){
                sequences.add(number);
            }
        }
        if(printSequence){
            for(int i : sequences){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return bored ? -1 : sequences.size();
    }

    public static boolean isPrime(int n){
        if(n==1 || (n != 2 && n%2 == 0)){
            return false;
        }
        for(int i=3; i*i < n; i+=2){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }

    public static void runGetMaxStockWorth(){
        System.out.println("\n\n**********Running Get Max Stock Worth**********\n");
        boolean printPrices = true;

        /*expected 745*/
        int[] prices = new int[]{100, 90};
        int[] stickers = new int[]{0, 0, 0, 0, 2, 1, 0, 0, 0};
        long start = System.currentTimeMillis();
        int result = getMaximumStockWorth(prices, stickers, printPrices);
        long end = System.currentTimeMillis();
        System.out.println("Expected[745] and result is " + result + ", taken " + (end-start) + "ms\n\n");

        /*expected 9*/
        prices = new int[]{9};
        stickers = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0};
        start = System.currentTimeMillis();
        result = getMaximumStockWorth(prices, stickers, printPrices);
        end = System.currentTimeMillis();
        System.out.println("Expected[9] and result is " + result + ", taken " + (end-start) + "ms\n\n");

        /*expected 988777*/
        prices = new int[]{123456};
        stickers = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        start = System.currentTimeMillis();
        result = getMaximumStockWorth(prices, stickers, printPrices);
        end = System.currentTimeMillis();
        System.out.println("Expected[988777] and result is " + result + ", taken " + (end-start) + "ms\n\n");

        /*expected 3297500*/
        prices = new int[]{10, 970019, 1976, 10936, 68750, 756309, 37600, 559601, 6750, 76091, 640, 312, 312, 90, 8870};
        stickers = new int[]{11, 2, 8, 10, 7, 6, 3, 1, 3};
        start = System.currentTimeMillis();
        result = getMaximumStockWorth(prices, stickers, printPrices);
        end = System.currentTimeMillis();
        System.out.println("Expected[3297500] and result is " + result + ", taken " + (end-start) + "ms\n\n");

        /*expected 198*/
        prices = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        stickers = new int[]{111, 111, 111, 111, 111, 111, 111, 111, 111};
        start = System.currentTimeMillis();
        result = getMaximumStockWorth(prices, stickers, printPrices);
        end = System.currentTimeMillis();
        System.out.println("Expected[198] and result is " + result + ", taken " + (end-start) + "ms\n\n");
    }

    public static int getMaximumStockWorth(int[] prices, int[] stickers, boolean printPrices){
        int result = 0;
        quickSort(prices, 0, prices.length-1, true);
        int maxNumber = prices[0];
        int maxDigits = 0;
        while(maxNumber > 0){
            maxDigits++;
            maxNumber /= 10;
        }
        if(maxDigits == 0){
            maxDigits = 1;
        }
        if(printPrices){
            for(int p : prices){
                System.out.print(p + " ");
            }
            System.out.println();
        }
        int[][] grid = new int[prices.length][maxDigits];

        int cnt = 0;

        /*logic to disassembly numbers to digits
        *with the least significant digit at the head and the most significant digit ath the tail
        */
        for(int i=0; i<prices.length; i++){
            int price = prices[i];
            int p = price;
            cnt = 0;
            while(p>0){
                grid[i][cnt] = p%10;
                p = p/10;
                cnt++;
            }
            if(cnt == 0){
                grid[i][cnt] = p;
                cnt++;
            }
            while(cnt < maxDigits){
                grid[i][cnt] = -1;
                cnt++;
            }

        }

        System.out.println();
        printMatrix(grid);
        System.out.println();

        /*logic to replace digit*/
        for(int d=maxDigits; d>0; d--){
            int[] digitIndices = new int[prices.length];
            int digitIdx = -1;
            int numberOfDigits = 0;
            /*getting indices (row number) for applicable digits in numbers
            *if number does not have digit at the location (row = i, col = d-1), set index to be -1
            **/
            for(int i=0; i<prices.length; i++){
                digitIdx++;
                if(grid[i][d-1] >= 0){
                    digitIndices[digitIdx] = i;
                    numberOfDigits++;
                }else{
                    digitIndices[digitIdx] = -1;
                }
            }

            for(int s=9; s>0 && numberOfDigits > 0; s--){
                int numberOfStickers = stickers[s-1];
                if(numberOfStickers == 0){
                    continue;
                }

                /*has enough stickers to replace all digits*/
                if(numberOfStickers >= numberOfDigits){
                    for(int i=0; i<digitIndices.length; i++){
                        /*only replace if i holds valid index (-1)
                        and value of digit at the location is smaller than sticker
                        */
                        if(digitIndices[i] >= 0
                            && grid[digitIndices[i]][d-1] >= 0
                            && s > grid[digitIndices[i]][d-1]){
//                            System.out.println("replace [" + s + "] with [" + grid[digitIndices[i]][d-1] + "] at [" + digitIndices[i] + "x" + (d-1) + "]");
                            grid[digitIndices[i]][d-1] = s;
                            /*reduce number of available stickers*/
                            stickers[s-1]--;
                            /*reduce number of digits can be replaced*/
                            numberOfDigits--;
                        }
                    }
                    break;
                }else{
                    while(numberOfStickers>0){
                        digitIdx = -1;
                        /*finding the smallest digit to swap
                        *if same digit occurs at more than 1 row, try to find the smallest sequence by comparing with descending digits
                        */
                        for(int i=0; i<digitIndices.length; i++){
                            if(digitIndices[i] < 0){
                                continue;
                            }
                            if(digitIdx == -1){ //initial step
                                digitIdx = i;
                            }else if(grid[digitIndices[i]][d-1] < grid[digitIdx][d-1]){ //find smaller digit
                                digitIdx = i;
                            }else if(grid[digitIndices[i]][d-1] == grid[digitIdx][d-1]){//find same digit in different row
                                int tmp = d-1;
                                while(tmp > 0){//moving backward to find the smallest sequence
                                    if(grid[i][tmp] > grid[digitIdx][tmp]){
                                        tmp = -1;
                                    }else if(grid[i][tmp] < grid[digitIdx][tmp]){
                                        digitIdx = i;
                                        tmp = -1;
                                    }else{
                                        tmp--;
                                    }
                                }
                            }
                        }
                        if(digitIdx != -1){
                            if(s <= grid[digitIndices[digitIdx]][d-1]){
                                break;
                            }
//                            System.out.println("replace [" + s + "] with [" + grid[digitIndices[digitIdx]][d-1] + "] at [" + digitIndices[digitIdx] + "x" + (d-1) + "]");
                            grid[digitIdx][d-1] = s;
                            /*reduce number of available stickers*/
                            stickers[s-1]--;
                            numberOfStickers--;
                            /*reduce number of digits can be replaced*/
                            numberOfDigits--;
                            /*set index at digitIdx to -1 so that we don't process it again*/
                            digitIndices[digitIdx] = -1;
                        }
                    }
                }
            }
        }
//        for(int d=maxDigits; d>0; d--){
//            for(int i=0; i<prices.length; i++){
//                if(grid[i][d-1] < 0){
//                    continue;
//                }
//                for(int s=9; s>0 && s > grid[i][d-1];s--){
//                    if(stickers[s-1] > 0){
//                        stickers[s-1]--;
//                        grid[i][d-1] = s;
//                        break;
//                    }
//                }
//            }
//        }

        /*logic to assembly digits to numbers*/
        for(int i=0; i<prices.length; i++){
            int[]tmp = grid[i];
            int price = 0;
            for(int c=0; c<tmp.length; c++){
                int p = tmp[c];
                if(p < 0){
                    break;
                }
                for(int j=0; j<c; j++){
                    p *= 10;
                }
                price += p;
            }

            prices[i] = price;
        }

        for(int i=0; i<prices.length; i++){
            result += prices[i];
            if(printPrices){
                System.out.print(prices[i] + " ");
            }
        }
        if(printPrices){
            System.out.println();
        }
        return result;
    }

    public static void quickSort(int[] numbers, int low, int high, boolean reversed){
        if(low >= high){
            return;
        }
        int middle = low + (high-low)/2;
        int pivot = numbers[middle];
        int i = low, j = high;
        while(i <= j){
            while((!reversed && numbers[i] < pivot)
                    || (reversed && numbers[i]> pivot)){
                i++;
            }
            while((!reversed && numbers[j] > pivot)
                    || (reversed && numbers[j] < pivot)){
                j--;
            }
            if(i <= j){
                if(numbers[i] != numbers[j]){
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
                i++;
                j--;
            }
        }
        if(low < i-1){
            quickSort(numbers, low, i-1, reversed);
        }
        if(i < high){
            quickSort(numbers, i, high, reversed);
        }
    }

    public static void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j] < 0){
                    break;
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
