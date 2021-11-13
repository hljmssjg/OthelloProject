import java.util.ArrayList;
import java.util.Collections;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(3);

        System.out.println(Collections.max(al));
        System.out.println(al.indexOf(Collections.max(al)));


    }
}
