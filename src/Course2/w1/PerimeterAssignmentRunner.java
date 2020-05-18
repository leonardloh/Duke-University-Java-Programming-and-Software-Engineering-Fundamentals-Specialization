package Course2.w1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int len = 0;
        for(Point point : s.getPoints()){
            len++;
        }
        return len;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestDist = 0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > largestDist){
                largestDist = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPoint = s.getLastPoint();
        double largestX = prevPoint.getX();
        for (Point point : s.getPoints()){
            int currPointX = point.getX();
            if (currPointX > largestX){
                largestX = currPointX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeter) {
                largestPerimeter = length;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        String largestFileName = "";
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerimeter) {
                largestPerimeter = length;
                largestFileName = f.getName();
            }
        }
        return largestFileName;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        double averageLength = getAverageLength(s);
        System.out.println("perimeter = " + length);
        System.out.println("avg perimeter = " + averageLength);
        System.out.println("longest side = " + getLargestSide(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest Perimeter: " + getLargestPerimeterMultipleFiles());

    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File with Largest Perimeter: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
//        pr.testPerimeterMultipleFiles();
    }
}
