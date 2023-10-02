import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        String result = "";
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0; i<a.length; i++){
            temp.add(a[i]);
        }
        while(temp.size()>0){
            int maxDigit = 0;
            for(String digit : temp){
                if(IsGreaterOrEqual(Integer.valueOf(digit), maxDigit)){
                    maxDigit = Integer.valueOf(digit);
                }
            }
            result += Integer.toString(maxDigit);
            temp.remove(Integer.toString(maxDigit));
        }
        return result;
    }

    static boolean IsGreaterOrEqual(int x, int y){
        int a = Integer.valueOf(Integer.toString(x) + Integer.toString(y));
        int b = Integer.valueOf(Integer.toString(y) + Integer.toString(x));
        return a>b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}