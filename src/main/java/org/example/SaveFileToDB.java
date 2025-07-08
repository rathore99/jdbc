package org.example;

public class SaveFileToDB {


}


//    String selectSQL = "SELECT photo FROM pictures WHERE name = ?";
//    PreparedStatement pstmt = conn.prepareStatement(selectSQL);
//pstmt.setString(1, "dog");
//
//        ResultSet rs = pstmt.executeQuery();
//        if (rs.next()) {
//        InputStream is = rs.getBinaryStream("photo");
//        FileOutputStream fos = new FileOutputStream("C:/images/output.jpg");
//
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        while ((bytesRead = is.read(buffer)) != -1) {
//        fos.write(buffer, 0, bytesRead);
//        }
//
//        fos.close();
//        is.close();
//        }
//


//    String insertSQL = "INSERT INTO pictures (name, photo) VALUES (?, ?)";
//    PreparedStatement pstmt = conn.prepareStatement(insertSQL);
//
//    File file = new File(filepath);
//    FileInputStream fis = new FileInputStream(file);
//
//pstmt.setString(1, "name");
//        pstmt.setBinaryStream(2, fis, (int) file.length());
//        pstmt.executeUpdate();
//
//        fis.close();
//        pstmt.close();
