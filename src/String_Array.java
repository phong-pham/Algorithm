import java.util.function.DoubleBinaryOperator;

/**
 * Created by phongpham on 4/24/15.
 */
public class String_Array {

    public static void main(String[] args){

        System.out.println("Do checking if string is unique");
        String str = "abc";
        System.out.println(str + " ::::" + checkIfStringUnique(str));
        str = "abacd";
        System.out.println(str + " ::::" + checkIfStringUnique(str));
        str = "hello";
        System.out.println(str + " ::::" + checkIfStringUnique(str));
        str = "world";
        System.out.println(str + " ::::" + checkIfStringUnique(str));
        str = "abA";
        System.out.println(str + " ::::" + checkIfStringUnique(str));
        str = "ab$%3*!";
        System.out.println(str + " ::::" + checkIfStringUnique(str));

        System.out.println("\n****************************************\n");
        System.out.println("Reverse a string");
        str = "abc";
        System.out.println("reverse [" + str + "] to [" + reverseString(str) + "]");
        str = "aaa";
        System.out.println("reverse [" + str + "] to [" + reverseString(str) + "]");
        str = "hello";
        System.out.println("reverse [" + str + "] to [" + reverseString(str) + "]");
        str = "world";
        System.out.println("reverse [" + str + "] to [" + reverseString(str) + "]");
        str = "phong";
        System.out.println("reverse [" + str + "] to [" + reverseString(str) + "]");

        System.out.println("\n****************************************\n");
        System.out.println("Remove duplicate characters");
        str = "abcd";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "aaaa";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "aabbb";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "abcabc";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "aacbb";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "hello world";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "abababa";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = null;
        System.out.println("process [" + str + "] to [" + (removeDuplicate(null)) + "]");
        str = "";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "I am a good man";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");
        str = "100";
        System.out.println("process [" + str + "] to [" + new String(removeDuplicate(str.toCharArray())) + "]");

        System.out.println("\n****************************************\n");
        System.out.println("Replace empty space with '%20'");
        str = "hello";
        System.out.println("replace empty space for[" + str + "] and get [" + replaceEmptySpace(str) + "]");
        str = "hello world";
        System.out.println("replace empty space for[" + str + "] and get [" + replaceEmptySpace(str) + "]");
        str = "I am a good man";
        System.out.println("replace empty space for[" + str + "] and get [" + replaceEmptySpace(str) + "]");

        System.out.println("\n****************************************\n");
        System.out.println("");

        System.out.println("\n****************************************\n");
        System.out.println("Get minimum number from string by removing n numbers");
        str = "12345";
        System.out.println("min from [" + str + "] by removing 2 is: " + getMinNumberByRemovingN(str, 2));
        str = "54321";
        System.out.println("min from [" + str + "] by removing 2 is: " + getMinNumberByRemovingN(str, 2));
        str = "11111";
        System.out.println("min from [" + str + "] by removing 2 is: " + getMinNumberByRemovingN(str, 2));
        str = "1";
        System.out.println("min from [" + str + "] by removing 2 is: " + getMinNumberByRemovingN(str, 2));
        str = "15364";
        System.out.println("min from [" + str + "] by removing 2 is: " + getMinNumberByRemovingN(str, 2));
        str = "15364";
        System.out.println("min from [" + str + "] by removing 4 is: " + getMinNumberByRemovingN(str, 4));

        System.out.println("\n****************************************\n");
        System.out.println("String to Int");
        str = "123";
        System.out.println("int value of [" + str + "] is: " + StringToInt(str));
        str = "12";
        System.out.println("int value of [" + str + "] is: " + StringToInt(str));
        str = "92340";
        System.out.println("int value of [" + str + "] is: " + StringToInt(str));
        str = "-123";
        System.out.println("int value of [" + str + "] is: " + StringToInt(str));
        str = "-12a3";
        System.out.println("int value of [" + str + "] is: " + StringToInt(str));

        System.out.println("\n****************************************\n");
        System.out.println("Replace characters in place");
        str = "HelloABWorld";
        System.out.println("Replace AB with C for [" + str + "] is: " + new String(replace(str.toCharArray())) + ".");
        str = "HelloAB";
        System.out.println("Replace AB with C for [" + str + "] is: " + new String(replace(str.toCharArray())) + ".");
        str = "HelloABC";
        System.out.println("Replace AB with C for [" + str + "] is: " + new String(replace(str.toCharArray())) + ".");
        str = "HelloWorld";
        System.out.println("Replace AB with C for [" + str + "] is: " + new String(replace(str.toCharArray())) + ".");
    }

    /**
     *
     * Find if String has all unique characters
     * Complexity: O(n) for time because it needs to iterate through the n-length string
     *
     */
    public static boolean checkIfStringUnique(String str){
        boolean result = true;
        boolean[] check = new boolean[256];
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(check[ch]){
                result = false;
                break;
            }else{
                check[ch] = true;
            }
        }
        return result;
    }

    /**
     * Reverse a string
     *
     */
    public static String reverseString(String in){
        String out = null;
        if(in != null){
            out = "";
            int len = in.length();
            while(len > 0){
                out += in.charAt(len-1);
                len--;
            }
        }
        return out;
    }

    /**
     * Remove duplicate character from a string without extra buffer
     */
    public static char[] removeDuplicate(char[] in){
        if(in != null && in.length > 2){
            int len = in.length;
            for(int i=0; i<len; i++){
                if(in[i] == 0){
                    continue;
                }
                for(int j=i+1; j<len; j++){
                    if(in[i] == in[j]){
                        in[j] = 0;
                    }
                }
            }

        }

        return in;
    }

    /**
     * Replace empty space with '%20'
     */
    public static String replaceEmptySpace(String str){
        if(str == null){
            return null;
        }else{
            char[] in = str.toCharArray();
            int spaceCnt = 0;
            for(int i=0; i<in.length; i++){
                if(in[i] == ' '){
                    spaceCnt++;
                }
            }
            int newLen = in.length + spaceCnt * 2;  //replace empty space ' ' with '%20'; therefore, we need 2 extra space
            char[] out = new char[newLen];
            int newIdx = 0;
            for(int i=0; i<in.length; i++){
                if(in[i] == ' '){
                    out[newIdx] = '%';
                    out[newIdx + 1] = '2';
                    out[newIdx + 2] = '0';
                    newIdx += 3;
                }else{
                    out[newIdx] = in[i];
                    newIdx++;
                }
            }
            return new String(out);
        }
    }

    public static String getMinNumberByRemovingN(String str, int n){
        String result = str;
        char[] arr = str.toCharArray();
        int length = arr.length;
        if(length <= n){
            result = "";
        }else{
            for(int i=0; i<n; i++){
                int max = 0;
                for(int j=0; j<length; j++){
                    if(arr[j] != 0 && arr[j] > arr[max]){
                        max = j;
                    }
                }
                arr[max] = 0;
            }
            result = new String(arr);
        }
        return result;
    }

    private static int[][] flipRowAndColumnToZero(int[][] input){
        if(input == null){
            return null;
        }else if(input.length == 0){
           return new int[0][0];
        }else{
            int[][] result = new int[][]{};
            return result;
        }
    }

    private static Integer StringToInt(String input){
        int number = 0;
        int i = 0;
        boolean isNegative = false;
        if(input.charAt(0) == '-'){
            isNegative = true;
            i = 1;
        }
//        while(i <= counter){
//            if(input.charAt(i) > '9' || input.charAt(i) < '0'){
//                return null;
//            }
//            int tmp = (input.charAt(counter) - '0') * (int)Math.pow(10d, new Double(j-counter));
//            number += tmp;
//            counter--;
//        }

        while( i < input.length() ){
            if(input.charAt(i) > '9' || input.charAt(i) < '0'){
                return null;
            }
            number *= 10;
            number += ( input.charAt(i++) - '0' );
        }
        if(isNegative){
            number *= -1;
        }
        return number;
    }

    public static char [] replace(char [] input) {
        if(input == null || input.length < 2){
            return input;
        }
        int len = input.length;
        int len1 = len;
        for(int i=0; i<len-1; i++){
            if(input[i] == 'A' && input[i+1] == 'B'){
                input[i] = 'C';
//                input[i+1] = '\0';
                len--;
                if((i+2) <= (len)){
                    input[i+1] = input[i+2];
                    for(int j=i+2; j<len; j++){
                        input[j] = input[j+1];
                    }
                    input[len] = '\0';
                }else{
                    input[i+1] = '\0';
                }
            }
        }
        System.out.println("array length changed from " + len + " to " + input.length);
        for(int i=0; i<input.length; i++){
            System.out.print(input[i] + "..");
        }
        System.out.println();
        return input;
    }

}
