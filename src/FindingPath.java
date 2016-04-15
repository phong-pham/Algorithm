import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by phongpham on 4/8/16.
 */
public class FindingPath {

    public static void main(String[] args){
        /****
         * S 1 1 1 1
         * 0 0 1 1 1
         * 1 1 0 E 1
         */
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("/Users/phongpham/Projects/Algorithm/src/path.txt")));
//            BufferedReader br = new BufferedReader(new FileReader(new File("path.txt")));
            String line = null;
            Location[][] grid = null;
            int rows = 3;
            int cols = 5;
            while((line = br.readLine()) != null){
                line = line.trim();
                if(line.length() == 0 && grid != null){
                    if(rows != grid.length || cols != grid[0].length){
                        System.out.println("Invalid Grid input");
                        break;
                    }
                    prepareGrid(grid);
                    printGrid(grid, false);
                    findPath(grid);
                    System.out.println("----------------------------------");
                }else{
                    String[] tmp = line.split(" ");
                    if(tmp.length == 2){
                        grid = new Location[Integer.valueOf(tmp[0])][Integer.valueOf(tmp[1])];
                    }else{
                        int rowIdx = Integer.valueOf(tmp[0]);
                        for(int i=1; i<tmp.length; i++){
                            grid[rowIdx][i-1] = new Location(i-1, rowIdx, Integer.valueOf(tmp[i]));
                        }
                    }
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

        /**
         * Randomly create grid
         */
        Location[][] grid = new Location[50][40];
        boolean hasExit = false;
        Random random = new Random();
        for(int i=0; i<50; i++){
            for(int j=0; j<40; j++){
                int locationType = Math.abs(random.nextInt()%(hasExit ? 2 : 3));
                if(locationType == 2){
                    hasExit = true;
                }
                grid[i][j] = new Location(j, i, locationType);
            }
        }
        if(!hasExit){
            System.out.println("Set exit point");
            int row = Math.abs(random.nextInt()%50);
            int col = Math.abs(random.nextInt()%40);
            grid[row][col].originalType = LocationType.EXIT;
            grid[row][col].locationType = LocationType.EXIT;
        }
//        prepareGrid(grid);
//        printGrid(grid, false);
//        findPath(grid);
    }

    public static void prepareGrid(Location[][]grid){
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Location loc = grid[i][j];
                loc.buildNeighbors(grid);
            }
        }
    }

    public static void findPath(Location[][]grid){
        Stack<Location> path = null;
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Location loc = grid[i][j];
                if(loc.locationType != LocationType.BLOCK){
                    System.out.println("At " + j + "x" + i);
                    path = new Stack<Location>();
                    doFindPath(loc, path, 0);
                    printResult(path);
                    resetLocation(grid);

                    path = new Stack<Location>();
                    doFindPathIteration(loc, path);
                    printResult(path);
                    resetLocation(grid);
                    System.out.println("\n");
                }
            }
        }
    }

    public static void printResult(Stack<Location> path){
        if(path.empty()){
            System.out.println("No exit.");
        }else{
            while(!path.isEmpty()){
                Location loc = path.remove(0);
                System.out.print(loc.getLocation() + " ");
            }
            System.out.println();
        }

    }

    /**
     * Recursive logic to find path from start location to exit.
     * In each step, the preferred neighbor will be calculated from start location.
     * If preferred neighbor is null, pop location from the path for going back to previous location
     * If preferred neighbor is not null, but it's a "dead-end" location (causing infinite loop), mark this neighbor location as unusable with with type BLOCK
     * Example for infinite loop: B -> A -> B may be OK because B can advance to C (B -> A -> B -> C).
     *                          But B -> A -> B -> A, the 2nd time A gets returned from B.getPreferredNeighbor, A will be marked as "dead-end"
     * @param start
     * @param path stack of locations have been visited prior to start location
     * @param level integer value shows how many times this recursion is called
     * @return
     */
    public static boolean doFindPath(Location start, Stack<Location> path, int level){
        if(start.locationType == LocationType.EXIT){
            path.push(start);
            return true;
        }
        boolean result = false;
        start.visitCnt++;

        Location pn = start.getPreferredNeighbor(true);
        if(pn != null && (pn.from == null || pn.from != start)){
            pn.from = start;
            path.push(start);
            result = doFindPath(pn, path, level + 1);
        }else{
            if(pn != null){
                pn.locationType = LocationType.BLOCK;
            }else if(!path.empty()){
                pn = path.pop();
            }

            if(pn != null && (level == 0 || !path.empty())){
                result = doFindPath(pn, path, level+1);
            }
        }

        return result;
    }

    /**
     * Logic to find path from start to exit with while loop to avoid StackOverFlow issue of recursion
     * Condition to stop the loop is when exit found or path is empty (when there is a infinite loop in the path)
     * Same logic with the above recursive logic.
     * @param start
     * @param path
     */
    public static void doFindPathIteration(Location start, Stack<Location> path){
        path.push(start);
        while(start.locationType != LocationType.EXIT && !path.empty()){
            start.visitCnt++;
            Location pn = start.getPreferredNeighbor(true);
            if(pn != null && (pn.from == null || pn.from != start)){
                pn.from = start;
                start = pn;
                path.push(start);
            }else{
                if(pn != null){
                    pn.locationType = LocationType.BLOCK;
                }
                start = path.pop();
            }

        }
    }

    /**
     * Helper function to reset location properties inside grid
     * @param grid
     */
    public static void resetLocation(Location[][]grid){
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                grid[i][j].reset();
            }
        }
    }

    /**
     * Helper function to print out grid information
     * @param grid
     * @param info true to print out detail of location;otherwise, print locationType of location only
     */
    public static void printGrid(Location[][] grid, boolean info){
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Location loc = grid[i][j];
                System.out.print((info ? loc : loc.locationType) + "\t");
            }
            System.out.println();
        }
    }

    static class Location{
        int x;
        int y;
        int visitCnt;
        LocationType locationType;
        LocationType originalType;
        List<Location> neighbors;

        /**
         * Location from which travels to this Location
         * A -> B then B.from = A
         */
        Location from;

        public Location(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.locationType = getLocationType(type);
            this.originalType = this.locationType;
            neighbors = new ArrayList<Location>();
        }

        public LocationType getLocationType(int lt){
            if(lt == 0){
                return LocationType.BLOCK;
            }else if(lt == 1){
                return LocationType.AVAILABLE;
            }else if(lt == 2){
                return LocationType.EXIT;
            }else{
                System.out.println(lt);
                return null;
            }
        }

        /**
         * Helper function to build neighbors for this location.
         * Assume robot can only move up, down, left and right
         * @param grid
         */
        public void buildNeighbors(Location[][]grid){
            if(locationType == LocationType.BLOCK){
                return;
            }
            int maxY = grid.length;
            int maxX = grid[0].length;
            if(y-1 >= 0 && grid[y-1][x].locationType != LocationType.BLOCK){
                neighbors.add(grid[y-1][x]);
            }
            if(x-1 >= 0 && grid[y][x-1].locationType != LocationType.BLOCK){
                neighbors.add(grid[y][x-1]);
            }
            if(x+1 < maxX && grid[y][x+1].locationType != LocationType.BLOCK){
                neighbors.add(grid[y][x+1]);
            }
            if(y+1 < maxY && grid[y+1][x].locationType != LocationType.BLOCK){
                neighbors.add(grid[y+1][x]);
            }

        }

        /**
         * Helper function to find the preferred neighbor from this location for next move.
         * The preferred neighbor is the location with the least visitCnt.
         * The logic prefers exploring new path to going back old path
         * @param checkLoop true to check if neighbor can advance to other location.
         *                  If true and neighbor is only able to advance back to this location, that neighbor is not selected
         * @return neighbor location
         */
        public Location getPreferredNeighbor(boolean checkLoop){
            Location result = null;
            if(neighbors.size() > 0){
                int minVisit = Integer.MAX_VALUE;
                for(int i=0; i<neighbors.size(); i++){
                    Location neighbor = neighbors.get(i);
                    if(neighbor.locationType == LocationType.EXIT){
                        result = neighbor;
                        break;
                    }
                    if(neighbor.locationType == LocationType.AVAILABLE && neighbor.visitCnt < minVisit){
                        if(!checkLoop
                                || ((neighbor = neighbor.getPreferredNeighbor(false)) != null && neighbor != this)){
                            result = neighbors.get(i);
                            minVisit = result.visitCnt;
                        }
                    }
                }
            }
            return result;
        }

        public String getLocation(){
            return x + "x" + y;
        }

        public void reset(){
            visitCnt = 0;
            from = null;
            locationType = originalType;
        }

        public String toString(){
            String str = "Location " + x + "x" + y + " is type[" + locationType + "], visits[" + visitCnt + "] times and has [" + neighbors.size() + "] neighbors.";
            return str;
        }
    }

    static enum LocationType {
        BLOCK,
        AVAILABLE,
        EXIT;

        public String toString(){
            return this.ordinal() + "";
        }
    }
}
