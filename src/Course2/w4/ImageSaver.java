package Course2.w4;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;

import java.awt.*;
import java.io.File;

public class ImageSaver {

    public void doSave()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            ImageResource inputImage = new ImageResource(f);
            String fname = inputImage.getFileName();
            String newFname = "copy-" + fname;
            inputImage.setFileName(newFname);
            inputImage.draw();
            inputImage.save();
        }
    }

    public static void main(String[] args) {
        ImageSaver is = new ImageSaver();
        is.doSave();
    }
}
