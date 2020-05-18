package Course4.w2.module1;

import processing.core.PApplet;

public class testMainTwo extends PApplet {
    private float yPos;

    public void setup() {
        size(200, 200);
        frameRate(30);
        yPos = (float) 0.00;
    }

    public void draw()
    {
        background(204);
        yPos = (float) (yPos - 1.0);
        if (yPos < 0) {
            yPos = height;
        }
        line(0, yPos, width, yPos);
    }

    public static void main (String[] args) {
        PApplet.main("Course4.w2.module1.testMainTwo", args);
    }
}
