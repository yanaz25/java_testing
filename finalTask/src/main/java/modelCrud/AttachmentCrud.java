package modelCrud;

import model.AttachmentModel;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentCrud extends Crud {
    private static final String TABLE_NAME = "attachment";
    private static final String ALL_COLUMNS = "id, content, content_type, test_id";
    private static final String ID_COLUMN = "id";
    private static final String CONTENT_COLUMN = "content";
    private static final String CONTENT_TYPE_COLUMN = "content_type";
    private static final String TEST_ID_COLUMN = "test_id";

    public AttachmentCrud() {
        super(TABLE_NAME);
    }

    public AttachmentModel getModel(ResultSet resultSet) {
        AttachmentModel model = null;
        try {
            int id = resultSet.getInt(ID_COLUMN);
            Blob content = resultSet.getBlob(CONTENT_COLUMN);
            String contentType = resultSet.getString(CONTENT_TYPE_COLUMN);
            int testId = resultSet.getInt(TEST_ID_COLUMN);
            model = new AttachmentModel(id, content, contentType, testId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return model;
    }

    public void insert(AttachmentModel attachmentModel) {
        insertRow(ALL_COLUMNS, attachmentModel.getId(), attachmentModel.getContent(), attachmentModel.getContentType(),
                attachmentModel.getTestId());
    }
}
