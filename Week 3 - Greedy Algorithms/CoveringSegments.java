import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        ArrayList<Integer> pts = new ArrayList<Integer>();

        ArrayList<Segment> segList = new ArrayList<Segment>();
        for(int i=0; i<segments.length; i++){
            segList.add(segments[i]);
        }

        while(segList.size()>0){
            int segEnd = segList.get(0).end;
            for(int i=1; i<segList.size(); i++){
                if(segList.get(i).end<segEnd){
                    segEnd = segList.get(i).end;
                }
            }
            pts.add(segEnd);
            for(int i=0; i<segList.size(); i++){
                if(segList.get(i).start<=segEnd){
                    segList.remove(i);
                    i--;
                }
            }
            
        }
        int[] points = new int[pts.size()];
        for(int i=0; i<pts.size(); i++){
            points[i] = pts.get(i);
        }
        
        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
