import java.util.*;

/**
 * Created by phongpham on 4/28/15.
 */
public class SDETest {

    public static void main(String[] args){
//        String str = "4\n";
//        str += "1 2 3 4\n";
//        str += "5 6 7 8\n";
//        str += "9 10 11 12\n";
//        str += "13 14 15 16\n";
//        duplicate();
        rotate();
        Set<String> test = new HashSet<String>();
    }

    public static void rotate(){

        /**
         *
         *
         1 2 3 4
         5 6 7 8
         9 10 11 12
         13 14 15 16

         5 1 2 23
         9 10 6 4
         13 11 7 8
         14 15 16 12
         -----
         1 2
         3 4

         3 1
         4 2
         */
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int row = 0;
        Integer[][] arr = new Integer[n][n];
        Integer[][] result = new Integer[n][n];
        boolean hasError = false;
        for(int i=0; i<n; i++){
            String str = sc.nextLine();
            String [] tmp = str != null ? str.trim().split(" ") : new String[0];
            if(tmp.length != n){
                hasError = true;
                i = n+1;
            }
            for(int j=0; !hasError && j<n; j++){
                arr[i][j] = Integer.valueOf(tmp[j]);
            }
            row++;
        }
        if(hasError || row != n){
            System.out.println("ERROR");
        }
        if(n == 1){
            result[0][0] = arr[0][0];
        }else{
            int numberOfRun = new Double(Math.ceil((double)n/2)).intValue();
            Set<String> processed = new HashSet<String>();
            for(int i=0; i<numberOfRun; i++){
                int top = i;
                int bottom = n-i-1;
                if(top == bottom){
                    result[top][bottom] = arr[top][bottom];
                    continue;
                }
                for(int r = 0; r<n; r++){           //running down
                    for(int r2 = 0; r2<n; r2++){    //running right
                        if(arr[r][r2] != null
                            && ((r == bottom || r == top)
                            || (r > top && r <= bottom && (r2 == top || r2 == bottom)))
                        ){
                            if(r2<bottom){
                                if(r == top){
                                    result[r][r2+1] = arr[r][r2];
                                    arr[r][r2] = null;
                                }else{
                                    if(r2 == top){
                                        result[r-1][top] = arr[r][r2];
                                        arr[r][r2] = null;
                                    }else{
                                        result[r][r2-1] = arr[r][r2];
                                        arr[r][r2] = null;
                                    }
                                }
                            }else if(r2 == bottom){
                                if(r<bottom){
                                    result[r+1][r2] = arr[r][r2];
                                    arr[r][r2] = null;
                                }else if(r == bottom){
                                    if(r2 == top){
                                        result[r-1][r2] = arr[r][r2];
                                        arr[r][r2] = null;
                                    }else{
                                        result[r][r2-1] = arr[r][r2];
                                        arr[r][r2] = null;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        if(!hasError){
//            System.out.println("Final result");
            printArr(result);
        }
    }

    public static void printArr(Integer[][] arr){
        int n = arr.length;
        for(int i=0; i<n; i++){
            String rowResult = "";
            for(int j=0; j<n; j++){
                rowResult += arr[i][j] + " ";
            }
            System.out.println(rowResult.trim());
        }
    }

    public static void duplicate(){

        //CASE 4 IS YES
        //CASE 5 IS NO
        //CASE 6 IS NO
        //CASE 7 IS YES
        //CASE 8 IS YES
        Scanner sc = new Scanner(System.in);
        int cnt = -1;
        int k = -1;
        int[][] arr = null;
        int row = 0;
        boolean dup = false;
        boolean hasError = false;
        do{
            try{
                String str = sc.nextLine().trim();
                String[] tmp = str.split(" ");
                if(tmp.length == 1){
                    if(cnt == -1){
                        cnt = Integer.valueOf(tmp[0]);
                    }else{
                        k = Integer.valueOf(tmp[0]);
                    }
                }else{
                    if(arr == null){
                        arr = new int[cnt][cnt];
                    }
                    if(tmp.length != cnt){
                        row = 0;
                        hasError = true;
                    }else{
                        for(int i=0; i<cnt; i++){
                            arr[row][i] = Integer.valueOf(tmp[i]);
                        }
                        row++;
                    }
                }
            }catch(Exception ex){
                row = 0;
                hasError = true;
            }
        }while(k < 0);
        if(k > cnt){
            System.out.println("NO");
        }else if(k == cnt){
            if(hasError){
                System.out.println("YES");  //CASE 7
            }else{
                System.out.println("NO");   //CASE 5
            }
        }else if(k <= 0){
            System.out.println("YES");
        }else if(row == 0){
            System.out.println("YES");      //CASE 4 and 8
        }else{

            Set<Integer> dupCheck = new HashSet<Integer>();
            List<Integer> rowData = new ArrayList<Integer>();
            for(int i=0; arr != null && i<arr.length && !hasError; i++){
                for(int j=0; j<k; j++){
                    if(dupCheck.contains(arr[i][j])){
                        System.out.println("YES");
                        dup = true;
                        break;
                    }else{
                        rowData.add(arr[i][j]);
                    }
                }
                if(!dup){
                    dupCheck.addAll(rowData);
                }
            }
            if(!dup){
                System.out.println("NO");
            }
        }

        /*
        Scanner sc = new Scanner(System.in);
        int cnt = -1;
        int k = -1;
        int[][] arr = null;
        int row = 0;
        boolean dup = false;
        boolean hasError = false;
        do{
            try{
                String str = sc.nextLine().trim();
                String[] tmp = str.split(" ");
                if(tmp.length == 1){
                    if(cnt == -1){
                        cnt = Integer.valueOf(tmp[0]);
                    }else{
                        k = Integer.valueOf(tmp[0]);
                    }
                }else{
                    if(arr == null){
                        arr = new int[cnt][cnt];
                    }
                    if(tmp.length != cnt){
                        row = 0;
                        hasError = true;
                    }else{
                        for(int i=0; i<cnt; i++){
                            arr[row][i] = Integer.valueOf(tmp[i]);
                        }
                        row++;
                    }
                }
            }catch(Exception ex){
                row = 0;
                hasError = true;
            }
        }while(k < 0 && !hasError);
        if(hasError){
            if(cnt <= 0 && k < 0){
                System.out.println("NO");
            }
            else{
                System.out.println("YES");
            }
        }else
        if(k > cnt){
            System.out.println("NO");
        }else if(k == cnt){
            if(hasError){
                System.out.println("YES");  //CASE 7
            }else{
                System.out.println("NO");   //CASE 5
            }
        }else if(k < 0){
            if(hasError){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }else if(k == 0){
            System.out.println("YES");
        }else if(row == 0){
            System.out.println("YES");      //CASE 4 and 8
        }else{

            Set<Integer> dupCheck = new HashSet<Integer>();
            List<Integer> rowData = new ArrayList<Integer>();
            for(int i=0; arr != null && i<arr.length && !hasError; i++){
                for(int j=0; j<k; j++){
                    if(dupCheck.contains(arr[i][j])){
                        System.out.println("YES");
                        dup = true;
                        break;
                    }else{
                        rowData.add(arr[i][j]);
                    }
                }
                if(!dup){
                    dupCheck.addAll(rowData);
                }
            }
            if(!dup){
                System.out.println("NO");
            }
        }
         */
    }
}
