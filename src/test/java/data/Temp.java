package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Temp {
     public static void main(String[] args) {
         System.out.println("hello");
        List<String> al = new ArrayList<String>();
         al.add("D");
         al.add("A");
         al.add("B");
         al.add("E");
         al.add("C");
         al.add("F");

        /* Collections.sort method is sorting the
        elements of ArrayList in ascending order. */
         Collections.sort(al);

         // Let us print the sorted list
         System.out.println(al);
         Collections.sort(al,Collections.reverseOrder());
         System.out.println(al);
    }
}
