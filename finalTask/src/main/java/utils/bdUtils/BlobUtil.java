package utils.bdUtils;

import java.sql.Blob;
import java.sql.SQLException;

import static utils.bdUtils.ConnectionUtil.getConnection;

public class BlobUtil {
    private static int firstBytePosition = 1;

    public static Blob getBlobFromBytes(byte[] bytes) {
        Blob blob = null;
        try {
            blob = getConnection().createBlob();
            blob.setBytes(firstBytePosition, bytes);
        } catch (SQLException sql) {
            sql.getErrorCode();
        }
        return blob;
    }
}
