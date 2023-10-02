import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    int gcd = gcd_naive(a,b);
    return (long) a * (long) b / (long) gcd;
  }

  private static int gcd_naive(int a, int b) {
    
    int c = a%b;
    if(c==0){
      return b;
    } else {
      return gcd_naive(b, c);
    }

  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_naive(a, b));
  }
}
