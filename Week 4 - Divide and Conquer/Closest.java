import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(int[] x, int y[]) {
        double ans = Double.POSITIVE_INFINITY;
        ArrayList<Point> points = new ArrayList<Point>();
        for(int i=0; i<x.length; i++){
            points.add(new Point((long) x[i], (long) y[i]));
        }
        points = mergeSort(points);
        ans = lengthSort(points);
        return ans;
    }

    private static ArrayList<Point> mergeSort(ArrayList<Point> a) {
        if(a.size() == 1){
            return a;
        }
        int m = a.size()/2;

        ArrayList<Point> b = new ArrayList<Point>();
        for(int i=0; i<m; i++){
            b.add(a.get(i));
        }
        ArrayList<Point> bNew = mergeSort(b);

        ArrayList<Point> c = new ArrayList<Point>();
        for(int i=m; i<a.size(); i++){
            c.add(a.get(i));
        }
        ArrayList<Point> cNew = mergeSort(c);

        ArrayList<Point> aNew = merge(bNew, cNew);
        return aNew;
    }

    private static ArrayList<Point> merge(ArrayList<Point> b, ArrayList<Point> c){
        ArrayList<Point> d = new ArrayList<Point>();
        long bEl = 0;
        long cEl = 0;
        while(b.size() > 0 && c.size() > 0){
            bEl = b.get(0).x;
            cEl = c.get(0).x;
            if(bEl <= cEl){
                d.add(b.get(0));
                b.remove(0);
            } else {
                d.add(c.get(0));
                c.remove(0);
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

    private static ArrayList<Point> mergeSortY(ArrayList<Point> a) {
        if(a.size() == 1){
            return a;
        }
        int m = a.size()/2;

        ArrayList<Point> b = new ArrayList<Point>();
        for(int i=0; i<m; i++){
            b.add(a.get(i));
        }
        ArrayList<Point> bNew = mergeSortY(b);

        ArrayList<Point> c = new ArrayList<Point>();
        for(int i=m; i<a.size(); i++){
            c.add(a.get(i));
        }
        ArrayList<Point> cNew = mergeSortY(c);

        ArrayList<Point> aNew = mergeY(bNew, cNew);
        return aNew;
    }

    private static ArrayList<Point> mergeY(ArrayList<Point> b, ArrayList<Point> c){
        ArrayList<Point> d = new ArrayList<Point>();
        long bEl = 0;
        long cEl = 0;
        while(b.size() > 0 && c.size() > 0){
            bEl = b.get(0).y;
            cEl = c.get(0).y;
            if(bEl <= cEl){
                d.add(b.get(0));
                b.remove(0);
            } else {
                d.add(c.get(0));
                c.remove(0);
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

    private static double lengthSort(ArrayList<Point> a) {
        if(a.size() == 1){
            return (double) Double.POSITIVE_INFINITY;
        }
        int m = a.size()/2;

        ArrayList<Point> b = new ArrayList<Point>();
        for(int i=0; i<m; i++){
            b.add(a.get(i));
        }
        double bNew = lengthSort(b);

        ArrayList<Point> c = new ArrayList<Point>();
        for(int i=m; i<a.size(); i++){
            c.add(a.get(i));
        }
        double cNew = lengthSort(c);

        double aNew = length(b, c, bNew, cNew);
        return aNew;
    }

    private static double length(ArrayList<Point> b, ArrayList<Point> c, double bNew, double cNew){
        ArrayList<Point> d = new ArrayList<Point>();
        double min = Math.min(bNew, cNew);
        long mid = b.get(b.size()-1).x;
        for(int i=0; i<b.size(); i++){
            if(b.get(i).x>=mid-min){
                d.add(b.get(i));
            }
        }
        for(int i=0; i<c.size(); i++){
            if(c.get(i).x<=mid+min){
                d.add(c.get(i));
            }
        }
        d = mergeSortY(d);
        for(int i=0; i<d.size(); i++){
            for(int j=i+1; j<d.size(); j++){
                if((d.get(j).y-d.get(i).y) > min){
                    j=d.size();
                } else if(getDistance(d.get(i), d.get(j))<min){
                    min = getDistance(d.get(i), d.get(j));
                }
            }
        }
        return min;
    }

    private static double getDistance(Point p1, Point p2){
        long x = p2.x-p1.x;
        long y = p2.y-p1.y;
        return Math.sqrt(x*x+y*y);
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
