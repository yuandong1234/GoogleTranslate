package com.yuong.translate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText etContent;
    private TextView tvResult;
    private ProgressBar progressBar;


    private TranslateCallback translateCallback = new TranslateCallback() {
        @Override
        public void onTranslateDone(String result) {
            Log.i(TAG, "result : " + result);
            progressBar.setVisibility(View.GONE);
            tvResult.setText(result);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    private void initView() {
        etContent = findViewById(R.id.content);
        tvResult = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_translate:
                translate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void translate() {
        String content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入内容...", Toast.LENGTH_SHORT).show();
            return;
        }
        tvResult.setText("");
        progressBar.setVisibility(View.VISIBLE);
        new TranslateUtil().translate(this, "auto", "ko", content, translateCallback);
    }

}
