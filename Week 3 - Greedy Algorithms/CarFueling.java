import java.util.*;
import java.io.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int tempDist = 0;
        int numStops = 0;
        int lastIndex = -1;
        int curIndex = 0;
        while(tempDist+tank<dist){
            for(int i=lastIndex+1; i<stops.length; i++){
                if(stops[i]-tempDist > tank){
                    break;
                }
                curIndex = i;
            }
            tempDist=stops[curIndex];
            if(lastIndex==curIndex){
                return -1;
            }
            lastIndex=curIndex;
            numStops++;
        }
        return numStops;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
