package utils;

import javax.imageio.ImageIO;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static boolean areImagesEqual(String actualImagePath, String expectedImagePath) {
        boolean isEqual = true;
        File actualImage = new File(actualImagePath);
        File expectedImage = new File(expectedImagePath);
        try {
            DataBuffer actualBuffered = ImageIO.read(actualImage).getData().getDataBuffer();
            DataBuffer expectedBuffered = ImageIO.read(expectedImage).getData().getDataBuffer();
            for (int i = 0; i < actualBuffered.getSize(); i++) {
                if (actualBuffered.getElem(i) != expectedBuffered.getElem(i)) {
                    isEqual = false;
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return isEqual;
    }
}
