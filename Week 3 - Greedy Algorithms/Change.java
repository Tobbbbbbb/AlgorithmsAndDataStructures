import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int num = 0;
        while(m >= 10){
        	num++;
        	m-=10;
        }
        while(m >= 5){
        	num++;
        	m-=5;
        }
        while(m >= 1){
        	num++;
        	m--;
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

