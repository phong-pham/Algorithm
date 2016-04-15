/**
 * Created by phongpham on 5/21/15.
 */
public class CleanRoom {

    public static void main(String[] args){

        boolean printStepResult = false;
        int[][] arr = new int[50][100];
        int[] location = new int[]{4,5};
        printArr(location);
        printMatrix(arr);

        doCleanUp(arr, location, printStepResult);

        System.out.println("Result is: ");
        printMatrix(arr);

        location = new int[]{25, 62};
        resetRoom(arr);
        doCleanUp(arr, location, printStepResult);

        System.out.println("Result is: ");
        printMatrix(arr);
    }

    public static void resetRoom(int[][]arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                arr[i][j] = 0;
            }
        }
    }

    public static int moveRobot(int[][] arr, int[] location, int facing){
        int rowIdx = location[0], colIdx = location[1];
        if(rowIdx >= arr.length || colIdx >= arr[0].length){
            System.out.println("Robot is not in the room, move it to the {0, 0}");
            location[0] = 0;
            location[1] = 0;
            return 1;
        }
        while(moveForward(arr, location, facing)){

        }
        facing = rotate(facing, 0);   //rotate left;
        while(moveForward(arr, location, facing)){

        }
        facing = rotate(facing, 0);
        return facing;
    }

    public static void doCleanUp(int[][] arr, int[] location, boolean printStepResult){
        int cnt = 0;
        int step = 0;

        int rotateDirection = 0;
        int facing = moveRobot(arr, location, 0);
        printArr(location);
        int newFacing = facing;
        do{
            if(!isClean(arr, location[0], location[1])){
                cleanUnit(arr, location[0], location[1]);
            }
            while(!moveForward(arr, location, newFacing)){
                newFacing = rotate(newFacing, rotateDirection);
            }
            if(newFacing != facing){
                facing = newFacing = rotate(newFacing, rotateDirection);
                rotateDirection = rotateDirection == 0 ? 1 : 0;
            }
            if(printStepResult){
                System.out.println("At step " + step);
                printMatrix(arr);
            }
            step++;
            cnt++;
        }while(cnt <= arr.length * arr[0].length);
    }



    public static void cleanUnit(int[][] arr, int rowIdx, int colIdx){
        arr[rowIdx][colIdx] = 1;
    }

    public static boolean isClean(int[][] arr, int rowIdx, int colIdx){
        return arr[rowIdx][colIdx] == 1;
    }

    // 0: left
    // 1: down
    // 2: right
    // 3: up
    public static int rotate(int facingDirection, int direction){
        int newFacingDirection = -1;
        if(direction == 0){
            newFacingDirection = facingDirection+1;
            if(newFacingDirection > 3){
                newFacingDirection = 0;
            }
        }else{
            newFacingDirection = facingDirection-1;
            if(newFacingDirection < 0){
                newFacingDirection = 3;
            }
        }
        return newFacingDirection;
    }

    public static boolean moveForward(int[][] arr, int[] currentLocation, int facingDirection){
        boolean result = true;
        int rowIdx = currentLocation[0];
        int colIdx = currentLocation[1];
        int newCell = 0;
        try{
            if(facingDirection == 0){   //left
                colIdx -= 1;
            }else if(facingDirection == 1){ //down
                rowIdx += 1;
            }else if(facingDirection == 2){ //right
                colIdx += 1;
            }else if(facingDirection == 3){ //up
                rowIdx -= 1;
            }
            newCell = arr[rowIdx][colIdx];
            currentLocation[0] = rowIdx;
            currentLocation[1] = colIdx;
        }catch (Exception ex){
//            ex.printStackTrace();
            result = false;
        }
        return result;
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
}
