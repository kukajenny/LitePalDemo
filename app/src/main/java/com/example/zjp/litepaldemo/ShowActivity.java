package com.example.zjp.litepaldemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zjp on 16-4-19.
 */
public class ShowActivity extends Activity implements OnClickListener {

    private EditText show_title;
    private EditText show_content;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initview();
        updateview();
    }

    public void initview(){
        show_title = (EditText)findViewById(R.id.show_title);
        show_content = (EditText)findViewById(R.id.show_content);
        save = (Button)findViewById(R.id.save);
    }
    public void updateview(){
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        show_title.setText(title);
        show_content.setText(content);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:{
                Intent intent = new Intent();
                intent.putExtra("return_title",show_title.getText().toString());
                intent.putExtra("return_content",show_content.getText().toString());
                //intent.putExtra("num",1);
                Log.d("mylog",show_title.getText()+""+show_content.getText());
                setResult(1, intent);
                finish();
            }
        }
    }
}
