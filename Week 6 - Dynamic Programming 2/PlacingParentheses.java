import java.util.Scanner;

public class PlacingParentheses {

    private static long[][] min;
    private static long[][] max;
    private static char[] op;

    private static long getMaximValue(String exp) {
        op = new char[(exp.length()-1)/2];
        long[] digit = new long[(exp.length()+1)/2];
        for(int i=0; i<(exp.length()-1)/2; i++){
            op[i] = exp.charAt(2 * i + 1);
            digit[i] = (long)(exp.charAt(2 * i)) - 48;
        }
        digit[digit.length-1] = (long)(exp.charAt(exp.length()-1)) - 48;

        min = new long[digit.length][digit.length];
        max = new long[digit.length][digit.length];

        for(int i=0; i<digit.length; i++){
            min[i][i] = digit[i];
            max[i][i] = digit[i];
        }

        long[] temp;
        for(int s=1; s<digit.length; s++){
            for(int i=0; i<digit.length-s; i++){
                int j = i+s;
                temp = MinAndMax(i, j);
                min[i][j] = temp[0];
                max[i][j] = temp[1];
            }
        }

        return max[0][digit.length-1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static long[] MinAndMax(int i, int j){
        long mi = Long.MAX_VALUE;
        long ma = Long.MIN_VALUE;
        for(int k=i; k<j; k++){
            long w = eval(max[i][k], max[k+1][j], op[k]);
            long x = eval(max[i][k], min[k+1][j], op[k]);
            long y = eval(min[i][k], max[k+1][j], op[k]);
            long z = eval(min[i][k], min[k+1][j], op[k]);
            mi = Math.min(mi, Math.min(Math.min(w,x), Math.min(y,z)));
            ma = Math.max(ma, Math.max(Math.max(w,x), Math.max(y,z)));
        }
        long[] toRet = {mi, ma};
        return toRet;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

