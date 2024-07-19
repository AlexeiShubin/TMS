package task;

import java.util.Scanner;

public class Performance {
    public static void performance(){
        Scanner scanner=new Scanner(System.in);
        String perf= scanner.next();
        while (!perf.equals("+")){
            System.out.println("если задание выполнено, то отметьте это плюсом");
            perf= scanner.next();
        }
    }
}
