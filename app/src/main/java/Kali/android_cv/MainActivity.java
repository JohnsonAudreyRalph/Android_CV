package Kali.android_cv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton Email, Phone, Git, Address, PDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
        Git = findViewById(R.id.Git);
        Address = findViewById(R.id.Address);
        PDF = findViewById(R.id.PDF);

        Email.setOnClickListener(v -> Email_Click());
        Phone.setOnClickListener(v -> Phone_Click());
        Git.setOnClickListener(v -> Git_Click());
        Address.setOnClickListener(v -> Address_Click());
        PDF.setOnClickListener(v -> PDF_Click());
    }

    void Email_Click(){
//        Toast.makeText(this, "Đã click Email", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hoangphi11343@gmail.com"});
        try {
            startActivity(Intent.createChooser(intent, "Chọn ứng dụng email"));
        } catch (android.content.ActivityNotFoundException ex) {
            // Nếu không tìm thấy Email trên thiết bị
            Toast.makeText(MainActivity.this, "Vui lòng đăng nhập Email trên thiết bị của bạn", Toast.LENGTH_SHORT).show();
        }
    }

    void Phone_Click(){
        String phoneNumber = "0918854405"; // Xác định số điện thoại hướng tới

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            startActivity(callIntent);
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Vui lòng cấp quyền truy cập " , Toast.LENGTH_SHORT).show();
        }
    }


    void Git_Click(){
        Intent intent = new Intent(this, Git.class);
        startActivities(new Intent[]{intent});
        // Toast.makeText(this, "Đã click GIT", Toast.LENGTH_SHORT).show();
    }

    void Address_Click(){
        // Toast.makeText(this, "Đã click Address", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:21.531262, 105.864576"));
        intent.setPackage("com.google.android.apps.maps");
        startActivities(new Intent[]{intent});
    }

    void PDF_Click(){
        // Toast.makeText(this, "Đã click PDF", Toast.LENGTH_SHORT).show();
        String URL_DOWNLOAD = "https://objects.githubusercontent.com/github-production-release-asset-2e65be/686577497/46e67d0e-1211-4013-9df9-33c932e6782f?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20230907%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20230907T051928Z&X-Amz-Expires=300&X-Amz-Signature=a6c0777fc753fc01023e98fb2bc1bcc1dcc84f8ce6dd32a7c5bf38bc5a5e4abe&X-Amz-SignedHeaders=host&actor_id=113893466&key_id=0&repo_id=686577497&response-content-disposition=attachment%3B%20filename%3DCurriculum_Vitae.pdf&response-content-type=application%2Foctet-stream";
        Download_PDF(URL_DOWNLOAD);
        Toast.makeText(this, "Đã tải xuống thành công", Toast.LENGTH_SHORT).show();
    }
    void Download_PDF(String pdf_URL){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pdf_URL)); // Tạo một đối tượng yêu cầu tài xuống (DownloadManager.Request), đối tượng được cấu hình từ URL
        request.setTitle("PDF DOWNLOAD");
        request.setDescription("Download the PDF File");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE); // Đưa ra thông báo trong quá trình tải xuống (Hiển thị trên thanh thông báo điện thoại)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Curriculum Vitae.pdf"); // Đặt vị trí tải xuống là thư mục tải xuống của bộ nhớ ngoài và đặt tên
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE); // Đối tượng này được sử dụng để quản lý quá trình tải xuống
        manager.enqueue(request); // Đưa ra yêu cầu tải xuống vào hàng đợi của DownloadManager ==> Sau khi gọi sẽ tự động quản lý quá trình tải xuống tệp vào thư mục đã chỉ định
    }
}