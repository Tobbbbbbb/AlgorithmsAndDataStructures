import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        double currentCap = (double) capacity;
        while(currentCap > 0){
            int maxIndex = -1;
            double maxNum  = -1;
            for(int i=0; i<values.length; i++){
                if (weights[i] > 0 && (double) values[i]/ (double) weights[i] > maxNum){
                    maxIndex = i;
                    maxNum = (double) values[maxIndex]/ (double) weights[maxIndex];
                }
            }
            if(maxIndex==-1){
                return value;
            }
            if(weights[maxIndex] <= currentCap){
                currentCap -= weights[maxIndex];
                weights[maxIndex] = 0;
                value += (double) values[maxIndex];
            } else {
                value += (double) values[maxIndex] / (double) weights[maxIndex] * currentCap;
                break;
            }
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
