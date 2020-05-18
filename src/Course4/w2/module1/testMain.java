package Course4.w2.module1;

import processing.core.PApplet;
import processing.core.PImage;

public class testMain extends PApplet {

    private PImage webImage;
    public void setup()
    {
        String url = "https://processing.org/img/processing-web.png";
        // Load image from a web server
        webImage = loadImage(url, "png");
    }

    public void draw()
    {
        background(0);
        image(webImage, 0, 0);
    }

    public static void main(String[] args) {
        PApplet.main("Course4.w2.module1.testMain", args);
    }
}
