package com.ccmt.code.submodule.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UnzipService {
    public String getValidFilePath(String filePath) throws IOException {
        int folderNameStartIndex = filePath.lastIndexOf("\\") + 1;
        String folderName = filePath.substring(folderNameStartIndex);
        String extension = folderName.substring(folderName.lastIndexOf(".") + 1);

        if (extension.equals("zip")) {
            String unzipFilePath = filePath.substring(0, filePath.lastIndexOf("."));
            File dir = new File(unzipFilePath);
            dir.mkdir();
            unzip(filePath, unzipFilePath);
            return unzipFilePath;
        }

        return filePath;
    }

    private boolean unzip(String filePath, String unzipFilePath) throws IOException {
        File destDir = new File(unzipFilePath);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            newFile.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return true;
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
