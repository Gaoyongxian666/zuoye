package com.example.gyx.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Person {
    private String name;
    private String url;
    private int image;

    public Person() {
    }

    public Person(String name,int url) {

        super();
        this.name = name;
        this.image = url;

    }

    public String getName() {
//this不能static
        return this.name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getUrl() {

        return this.url;

    }

    public void setImage(int url) {

        this.image = url;

    }
    public int getImage() {

        return this.image;

    }

    public void setUrl(String url) {

        this.url = url;

    }


}


public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        final List<Person> personlist=new ArrayList<Person>();

        // imageView.setImageBitmap(bitmap);
        // imageView.setImageURI(Uri.fromFile(new File("/sdcard/test.jpg")));
        // bitmap = BitmapFactory.decodeStream(is);

        Person p1=new Person("gaoyongxian",R.drawable.demo01);
        Person p2=new Person("gaoyongxian01",R.drawable.demo01);
        Person p3=new Person("gaoyongxian02",R.drawable.demo01);
        Person p4=new Person("gaoyongxian03",R.drawable.demo01);
        Person p5=new Person("gaoyongxian04",R.drawable.demo01);
        personlist.add(p1);
        personlist.add(p2);
        personlist.add(p3);
        personlist.add(p4);
        personlist.add(p5);


        //为什么baseadapter不行呢？？

        final MyAdapter myAdapter = new MyAdapter(MainActivity.this,R.layout.item, personlist);
        listView.setAdapter(myAdapter);


//        int [] a={R.id.name,R.id.touxiang};
//        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
//        HashMap<String, Object> map=new HashMap<>();
//        map.put("image", personlist.get(0).getImage());
//        map.put("name",personlist.get(0).getName());
//        list.add(map);
//        SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,list,R.layout.item,new String[]{"name","image"},a);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=personlist.get(position).getName();
                AlertDialog.Builder myalert=new AlertDialog.Builder(MainActivity.this);
                myalert.setMessage("他的名字："+name+"\n"+"他的性别："+"男"+"\n"+"他的分数："+"90"+"\n");
                myalert.setNegativeButton("取消",null);
                myalert.setPositiveButton("确定",null);
                myalert.show();
            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name=personlist.get(position).getName();
                Intent startIntent = new Intent(MainActivity.this,Main2Activity.class);
                startIntent.putExtra("name",name);
                startActivity(startIntent);
                return true;
            }
        });
    }




}
