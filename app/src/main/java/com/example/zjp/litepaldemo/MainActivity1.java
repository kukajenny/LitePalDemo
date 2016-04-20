package com.example.zjp.litepaldemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zjp.litepaldemo.adapter.NewsAdapter;
import com.example.zjp.litepaldemo.model.News;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.litepal.util.Const;

import java.util.List;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Button load;
    private Button insert;
    private Button del;
    private Button update;
    private Button query;
    private ListView listview;
    private List<News> allnew;
    private NewsAdapter adapter;
    private int mposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        updateview();
    }

    public void initview(){
        load = (Button)findViewById(R.id.load);
        insert = (Button) findViewById(R.id.insert);
        del = (Button) findViewById(R.id.del);
        update = (Button) findViewById(R.id.update);
        query = (Button)findViewById(R.id.query);
        listview = (ListView)findViewById(R.id.list);
    }

    public void updateview(){
        load.setOnClickListener(this);
        insert.setOnClickListener(this);
        del.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        Toast.makeText(this, "数据插入成功",Toast.LENGTH_SHORT).show();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity1.this, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();

                mposition = position;
                News news = allnew.get(position);
                Intent intent = new Intent(MainActivity1.this, ShowActivity.class);
                intent.putExtra("title", news.getTitle());
                intent.putExtra("content", news.getContent());
                startActivityForResult(intent, 1);

            }
        });
    }

    public void load(){
        SQLiteDatabase db = Connector.getDatabase();
        Toast.makeText(this, "数据库加载成功",Toast.LENGTH_SHORT).show();
    }
    public void insert(){
        News news = new News();
        news.setTitle("abc");
        news.setContent("123");
        news.save();
        Toast.makeText(this, "数据插入成功",Toast.LENGTH_SHORT).show();
    }
    public void update(){
        News news = DataSupport.find(News.class, 2);
        news.setContent("dfdf");
        news.save();

        /*News updateNews = new News();
        updateNews.setTitle("dfdf");
        updateNews.updateAll("title = ? and commentcount > ?", "abc", "0");*/

        Toast.makeText(this, "数据更新成功",Toast.LENGTH_SHORT).show();

    }

    public void query(){

         allnew = DataSupport.findAll(News.class);

        adapter = new NewsAdapter(this,R.layout.list_item,allnew);
        listview.setAdapter(adapter);

        Toast.makeText(this, "数据查询成功",Toast.LENGTH_SHORT).show();
    }
    public void delete(){
        DataSupport.delete(News.class, 1);
        //DataSupport.deleteAll(News.class, "title = ? and content = ?", "abc", "123");
        Toast.makeText(this, "数据删除成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load: load();break;
            case R.id.insert:insert();
                break;
            case R.id.del: delete();break;
            case R.id.update: update();break;
            case R.id.query:query();break;
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mylog", requestCode + "yes" + resultCode);
        switch (requestCode){
            case 1:
                Log.d("mylog", resultCode + " " + requestCode);
                String title = data.getStringExtra("return_title");
                String content = data.getStringExtra("return_content");
                News news = new News();
                news.setTitle(title);
                news.setContent(content);
                allnew.set(mposition, news);
                adapter.notifyDataSetChanged();
        }
    }

}
