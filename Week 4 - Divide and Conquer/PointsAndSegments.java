import java.util.*;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> end = new ArrayList<Integer>();
        for(int i=0; i<starts.length; i++){
            start.add(starts[i]);
            end.add(ends[i]);
        }
        start = mergeSort(start);
        end = mergeSort(end);
        
        int newStarts[] = new int[starts.length];
        int newEnds[] = new int[ends.length];
        
        for(int i=0; i<starts.length; i++){
            newStarts[i] = start.get(i);
            newEnds[i] = end.get(i);
        }

        int l = 0;
        int r = 0;
        int[] toRet = new int[points.length];
        for(int i=0; i<points.length; i++){
            l = binarySearchLast(newStarts, points[i]);
            r = binarySearchFirst(newEnds, points[i]);
            toRet[i] = l - r;
        }
        return toRet;
    }

    private static ArrayList<Integer> mergeSort(ArrayList<Integer> a) {
        if(a.size() == 1){
            return a;
        }
        int m = a.size()/2;

        ArrayList<Integer> b = new ArrayList<Integer>();
        for(int i=0; i<m; i++){
            b.add(a.get(i));
        }
        ArrayList<Integer> bNew = mergeSort(b);

        ArrayList<Integer> c = new ArrayList<Integer>();
        for(int i=m; i<a.size(); i++){
            c.add(a.get(i));
        }
        ArrayList<Integer> cNew = mergeSort(c);

        ArrayList<Integer> aNew = merge(bNew, cNew);
        return aNew;
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> b, ArrayList<Integer> c){
        ArrayList<Integer> d = new ArrayList<Integer>();
        int bEl = 0;
        int cEl = 0;
        while(b.size() > 0 && c.size() > 0){
            bEl = b.get(0);
            cEl = c.get(0);
            if(bEl <= cEl){
                b.remove(0);
                d.add(bEl);
            } else {
                c.remove(0);
                d.add(cEl);
            }
        }
        if(b.size()>0){
            for(int i=0; i<b.size(); i++){
                d.add(b.get(i));
            }
        }
        if(c.size()>0){
            for(int i=0; i<c.size(); i++){
                d.add(c.get(i));
            }
        }
        return d;
    }

    static int binarySearchFirst(int[] a, int x) {
        int left = 0, right = a.length;
        while(true){
            int mid = (left + right)/2;
            if(a[mid]==x){
                while(mid>left && a[mid-1]==x){
                    mid--;
                }
                return mid;
            } else if(a[mid]>x) {
                if(mid==left){
                    return mid;
                }
                right=mid;
            } else{
                if(mid==right-1){
                    return mid+1;
                }
                left=mid+1;
            }
        }
    }

    static int binarySearchLast(int[] a, int x) {
        int left = 0, right = a.length;
        while(true){
            int mid = (left + right)/2;
            if(a[mid]==x){
                while(mid<right-1 && a[mid+1]==x){
                    mid++;
                }
                return mid+1;
            } else if(a[mid]>x) {
                if(mid==left){
                    return mid;
                }
                right=mid;
            } else{
                if(mid==right-1){
                    return mid+1;
                }
                left=mid+1;
            }
        }
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

