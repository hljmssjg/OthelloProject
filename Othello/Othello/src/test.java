import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        int timeLimit = 5;
        long endTime = startTime+timeLimit*1000;
        while(System.currentTimeMillis() < endTime){
            SimpleDateFormat dd = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            Date d = new Date();
            System.out.println(dd.format(d));
        }




    }
}
