package com.jabulani.ligiopen.dao.fileDao;

import com.jabulani.ligiopen.model.aws.File;

public interface FileDao {
    File getFileById(Integer fileId);
}
