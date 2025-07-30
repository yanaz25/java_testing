package utils.fileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import java.util.Base64;

public class ImageUtil {
    public static boolean areImagesEqual(byte[] actualImage, byte[] expectedImage) {
        boolean isEqual = true;
        DataBuffer actualImageBuffer = getDataBufferFromBytes(actualImage);
        DataBuffer expectedImageBuffer = getDataBufferFromBytes(expectedImage);
        for (int i = 0; i < actualImageBuffer.getSize(); i++) {
            if (actualImageBuffer.getElem(i) != expectedImageBuffer.getElem(i)) {
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }

    private static DataBuffer getDataBufferFromBytes(byte[] image) {
        DataBuffer dataBuffer = null;
        try {
            BufferedImage expectedImage = ImageIO.read(new ByteArrayInputStream(image));
            dataBuffer = expectedImage.getData().getDataBuffer();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return dataBuffer;
    }

    public static byte[] getScreenshotBytes(String bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
