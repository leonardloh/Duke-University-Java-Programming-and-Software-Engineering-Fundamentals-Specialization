package Course2.w4;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchGrayScale {

    public ImageResource makeGray (ImageResource inImage)
    {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels())
        {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void selectAndConvert()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage =  makeGray(inImage);
            outImage.draw();
            String fname = inImage.getFileName();
            String newFname = "gray-" + fname;
            outImage.setFileName(newFname);
            outImage.save();
        }
    }

    public static void main(String[] args) {
        BatchGrayScale bgs = new BatchGrayScale();
        bgs.selectAndConvert();
    }
}
