import java.util.Calendar;
import java.util.Date;

/**
 * Created by phongpham on 10/6/15.
 */
public class MainTest {

    public static void main(String[] args){
//        Test t1 = new Test("t1");
//        Test t2 = new Test("t2");
//        t1.addString("t1");
//        t2.addString("t2");
//        System.out.println("t1.toString(): " + t1.toString());
//        System.out.println("t2.toString(): " + t2.toString());
        String forward = "\n", backward = "\n";
        Date dt = new Date();
        for(int i=0; i<100; i++){
            Date result = adjustDateByOffset(dt, (i+1), true);
            forward += (i+1) + " days forward from [" + dt + "] is " + result + "\n";
            result = adjustDateByOffset(dt, (-1)*(i+1), true);
            backward += (i+1) + " days backward from [" + dt + "] is " + result + "\n";
        }
        System.out.println(forward);
        System.out.println("\n" + backward);
    }



    public static Date adjustDateByOffset(Date dt, int offset, boolean excludeSunday){
        Calendar cal = Calendar.getInstance();
        if(dt != null){
            cal.setTime(dt);
        }
        if(offset != 0){
            if(excludeSunday){
                int multiplier = offset > 0 ? 1 : -1;
                offset = Math.abs(offset);
//                for(int i=1; i<=offset;i++){
//                    cal.add(Calendar.DAY_OF_MONTH, multiplier);
//                    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
//                        i--;
//                    }
//                }
                do{
                    int offsetToSunday = cal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
                    if(multiplier > 0){
                        offsetToSunday = 7 - offsetToSunday;
                    }
                    if(offsetToSunday > offset){
                        cal.add(Calendar.DAY_OF_MONTH, multiplier * offset);
                        offset = 0;
                    }else if(offsetToSunday == offset){
                        cal.add(Calendar.DAY_OF_MONTH, multiplier * (offset+1));
                        offset = 0;
                    }else{
                        cal.add(Calendar.DAY_OF_MONTH, multiplier * offsetToSunday);
                        offset -= offsetToSunday;
                        int numberOfSunday = (offsetToSunday != 0 ? 1 : 0) + offset/7;
                        int remainder = offset%7;
                        if(numberOfSunday > 1){
                            cal.add(Calendar.DAY_OF_MONTH, multiplier * 7 * (numberOfSunday-1));
                        }
                        cal.add(Calendar.DAY_OF_MONTH, multiplier * remainder);
                        offset = numberOfSunday;
                    }
                }while(offset > 0);

//                do{
//                    cal.add(Calendar.DAY_OF_MONTH, multiplier * (numberOfSunday * 7));
//                    if(numberOfSunday < 7){
//                        cal.add(Calendar.DAY_OF_MONTH, multiplier * remainder);
//                        numberOfSunday = 0;
//                    }else{
//                        cal.add(Calendar.DAY_OF_MONTH, multiplier * remainder);
//                        remainder = numberOfSunday%7;
//                    }
//                    if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
//                        cal.add(Calendar.DAY_OF_MONTH, multiplier * (numberOfSunday+1));
//                    }
//                    numberOfSunday = numberOfSunday/7;
//                }while(numberOfSunday > 0);

            }else{
                cal.add(Calendar.DAY_OF_MONTH, offset);
            }
        }
        return cal.getTime();
    }
}
