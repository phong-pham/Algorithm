import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongpham on 10/6/15.
 */
public class Test {

    private static List<String> stringList;
    private List<String> stringListNonStatic;

    private String name;

    public Test(String n){
        name = n;
    }

    public void instantiateList(){
        if(stringList == null){
            stringList = new ArrayList<String>();
            System.out.println(this.name + " instantiates stringList");
        }
        if(stringListNonStatic == null){
            this.stringListNonStatic = new ArrayList<String>();
            System.out.println(this.name + " instantiates stringListNonStatic");
        }
    }

    public void addString(String str){
        instantiateList();
        stringList.add(str);
        stringListNonStatic.add(str);
    }

    public String toString(){
        String result= name + ": static is ";
        for(int i=0; i<stringList.size(); i++){
            result += stringList.get(i);
            if(i<stringList.size()-1){
                result += ",";
            }
        }
        result += "; non-static is ";
        for(int i=0; i<stringListNonStatic.size(); i++){
            result += stringListNonStatic.get(i);
            if(i<stringListNonStatic.size()-1){
                result += ",";
            }
        }
        return result;
    }
}
