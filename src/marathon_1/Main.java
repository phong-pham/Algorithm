package marathon_1;

/**
 * Created by phongpham on 6/1/15.
 */
public class Main {

    public static void main(String[] args){
        int a = 40;
        int b = 2;
        System.out.println(add(a, b));
        System.out.println(a);
    }

    public static int add(int a, int b){
        a = a + b;
        return a;
    }
}
