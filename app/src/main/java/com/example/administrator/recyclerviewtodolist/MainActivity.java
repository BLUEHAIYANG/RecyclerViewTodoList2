package com.example.administrator.recyclerviewtodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<TodoWhat> todoWhatList;
    TodoWhat todoWhatEmpty = new TodoWhat();
    MyAdapter myAdapter;
    //2.在子项布局和父布局中添加控件
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //给数组加几个元素；

        todoWhatList=DataSupport.findAll(TodoWhat.class);
        Log.e("size--------",String.valueOf(todoWhatList.size()));
        myAdapter =new MyAdapter(todoWhatList,this);
        /*if (todoWhatList==null) {
            initArr();
            myAdapter.notifyDataSetChanged();
        }*/

        //3.实例化一个RecyclerView对象，想传入数据，

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 开始做适配器；

        recyclerView.setAdapter(myAdapter);
        //10.创建主视图实例；
        Button addButton = (Button) findViewById(R.id.button_add);
        //11.给主视图上的控件button添加监听器；
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoWhatList.add(todoWhatEmpty);
                myAdapter.notifyDataSetChanged();
                Log.d(TAG, "onClick: ");
            }
        });

        Button saveButton=(Button)findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TodoWhat> tempList=new ArrayList<>();
                tempList=todoWhatList;
                DataSupport.deleteAll(TodoWhat.class);
                for (int i=0;i<todoWhatList.size();i++)
                    todoWhatList.get(i).clearSavedState();
                DataSupport.saveAll(tempList);
                Log.e("save click---",String.valueOf(tempList.size()));
                Log.e("save click---",String .valueOf(tempList.get(0).getTitle()) );


            }
        });
    }

   /* @Override
    protected void onStop() {
        super.onStop();
        List<TodoWhat> tempList=new ArrayList<>();
        tempList=todoWhatList;
        DataSupport.deleteAll(TodoWhat.class);
        DataSupport.saveAll(tempList);
    }*/

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        List<TodoWhat> tempList=new ArrayList<>();
        tempList=todoWhatList;
        DataSupport.deleteAll(TodoWhat.class);
        DataSupport.saveAll(tempList);
    }*/

    private void initArr(){
        for (int i=0;i<3;i++){
            todoWhatList.add(todoWhatEmpty);
        }



}



}





