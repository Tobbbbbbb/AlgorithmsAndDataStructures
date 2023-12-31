import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int currentNum = 1;
        int temp = n;
        while(temp>=currentNum){
            summands.add(currentNum);
            temp-=currentNum;
            currentNum++;
        }
        summands.set(summands.size()-1, summands.get(summands.size()-1)+temp); 
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

