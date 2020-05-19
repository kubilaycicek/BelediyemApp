package com.ketechsoft.reqtrack.utils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageConverterUtil {
    public static String ConvertImageFileToBase64String(String imagePath) throws IOException {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    public static void ConvertBase64ToImageFile(String base64Image, String pathFile) throws IOException {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
/*
    public static String saveImageAndGetImageUrlToString(String base64String) throws IOException {
        Path filePath;

        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();

        filePath = Paths.get(root.toString(),"src", "main", "resources", "static","images",java.util.UUID.randomUUID()+ ".jpg");

        ImageConverterUtil.ConvertBase64ToImageFile(base64String,filePath.toFile().getAbsolutePath());

        System.out.println("New File : "+ filePath.toFile().getPath());
        System.out.println("New Image : "+ filePath.getFileName().toString());

        return filePath.getFileName().toString();
    }
    */

}
