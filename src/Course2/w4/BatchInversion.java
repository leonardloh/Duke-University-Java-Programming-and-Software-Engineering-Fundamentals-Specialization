package Course2.w4;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversion {

    public ImageResource makeInverse (ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels())
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage =  makeInverse(inImage);
            outImage.draw();
            String fname = inImage.getFileName();
            String newFname = "inverted-" + fname;
            outImage.setFileName(newFname);
            outImage.save();
        }
    }

    public static void main(String[] args) {
        BatchInversion bi = new BatchInversion();
        bi.selectAndConvert();
    }

}
