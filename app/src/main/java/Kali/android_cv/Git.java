package Kali.android_cv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Git extends AppCompatActivity {

    WebView Git;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git);

        Git = findViewById(R.id.Load_Git);

        Git.setWebViewClient(new WebViewClient());
        Git.loadUrl("https://github.com/JohnsonAudreyRalph?tab=repositories");
    }

    @Override
    public void onBackPressed() {
        if (Git.canGoBack()){
            Git.goBack();
        }else {
            super.onBackPressed();
        }
    }
}