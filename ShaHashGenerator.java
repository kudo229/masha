import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ShaHashGenerator {

    public static void main(String[] args) {

        String message = "Đây là tin nhắn của tôi";
        String folderName = "thư_mục_của_tôi";

        // Tạo thư mục
        File directory = new File(folderName);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.println("Tạo thư mục thất bại!");
                return;
            }
        }

        // Tạo mã băm SHA-256
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            String hash = bytesToHex(encodedHash);

            // Ghi mã băm vào tệp
            File hashFile = new File(directory, "hash.txt");
            FileWriter writer = new FileWriter(hashFile);
            writer.write(hash);
            writer.close();

            System.out.println("Mã băm được tạo và lưu tại: " + hashFile.getAbsolutePath());

        } catch (NoSuchAlgorithmException | IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Hàm phụ để chuyển đổi mảng byte sang chuỗi hex
    private static String bytesToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
