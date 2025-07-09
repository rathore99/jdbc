package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveFileToDB {

    public static void main(String[] args) {

        String selectSQL = "SELECT file FROM pictures WHERE file_name = ?";
        try {
            Connection conn = DbConnection.getConnection();
            storeImage(conn,"C:\\rahul-projects\\jdbc\\src\\main\\resources\\db_arch_img.png");
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, "db_arch.png");

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("file");
                FileOutputStream fos = new FileOutputStream("C:\\rahul-projects\\jdbc\\src\\main\\resources\\output.png");

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
                is.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void storeImage(Connection conn,String filepath) {

        String insertSQL = "INSERT INTO pictures (file_name, file) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            File file = new File(filepath);
            FileInputStream fis = new FileInputStream(file);

            pstmt.setString(1, "db_arch.png");
            pstmt.setBinaryStream(2, fis, (int) file.length());
            pstmt.executeUpdate();

            fis.close();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}






