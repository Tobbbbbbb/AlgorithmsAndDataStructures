import java.util.*;

public class GCD {
  private static int gcd(int a, int b) {
    
    int c = a%b;
    if(c==0){
      return b;
    } else {
      return gcd(b, c);
    }

  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd(a, b));
  }
}
