package ru.kpfu.itis.kirillakhmetov.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CloudinaryUtil {
    private static Cloudinary cloudinary = getInstance();
    private static final String CLOUD_NAME = "cloud_name";
    private static final String API_KEY = "api_key";
    private static final String API_SECRET = "api_secret";
    private static final String MY_CLOUD_NAME = "cloudinary.cloud.name";
    private static final String MY_API_KEY = "cloudinary.api.key";
    private static final String MY_API_SECRET = "cloudinary.api.secret";
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final int DIRECTORIES_COUNT = 10;

    private static Cloudinary getInstance() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    CLOUD_NAME, ConfigReader.getValue(MY_CLOUD_NAME),
                    API_KEY, ConfigReader.getValue(MY_API_KEY),
                    API_SECRET, ConfigReader.getValue(MY_API_SECRET)
            ));
        }
        return cloudinary;
    }

    public static String uploadImage(InputStream image, String submittedFileName) throws IOException {
        String filename = Paths.get(submittedFileName).getFileName().toString();
        File tempFile = createTemporaryFile(filename, image);
        Map<String, String> uploadInfo = cloudinary.uploader().upload(tempFile, Map.of());
        if (tempFile.exists()) {
            tempFile.delete();
        }
        return uploadInfo.get("url");
    }

    private static File createTemporaryFile(String filename, InputStream image) throws IOException {
        File uploadDir = new File(UPLOAD_DIRECTORY + File.separator + Math.abs(filename.hashCode() % DIRECTORIES_COUNT));
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File tempFile = new File(uploadDir, filename);
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(image);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile))) {
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(bytesRead);
            }
        }
        return tempFile;
    }
}
