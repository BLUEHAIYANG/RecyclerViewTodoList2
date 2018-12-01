package com.example.administrator.recyclerviewtodolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {//继承的Adapter类型是个泛型
    //3.写适配器；适配器的属性是一个泛型数组；写出适配器的构造函数
    private static final String TAG = "MyAdapter";
    private List<TodoWhat> marr=new ArrayList<>() ;
    private Context context;


    public MyAdapter(List<TodoWhat>arr,Context context){
        marr =arr;
        this.context = context;
    }

    //4.创建内部类ViewHolder,依然是属性+构造器；
    static class ViewHolder extends RecyclerView.ViewHolder{
        EditText editText;
        Button delButton;
        Button saveButton;
        Button reStoreButton;

        public ViewHolder(View view){
            super(view);
            editText =(EditText) view.findViewById(R.id.edit_text);
            delButton=(Button) view.findViewById(R.id.button_del);
            reStoreButton=(Button)view.findViewById(R.id.button_reStore);
        }
    }
    //5.重写这三大方法
    @Override
    public int getItemCount() {
        return marr.size();//这个方法干嘛用的！！！！
    }
    //6.初始化ViewHolder对象，借助存放了空子项布局的view因为要将这个view传入ViewHolder构造器；
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        final View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,false);//先隐形膨胀一下
        final ViewHolder viewHolder= new ViewHolder(view);

        viewHolder.reStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getLayoutPosition();
                if(position>=0) {
                    TodoWhat todoWhat = new TodoWhat();
                    todoWhat.setTitle(viewHolder.editText.getText().toString());
                    todoWhat.setPosition(position);
                    marr.set(position,todoWhat);
                    Toast.makeText(context,"此项更新成功！",Toast.LENGTH_SHORT).show();

                    //todoWhat.updateAll("position=?",String.valueOf(todoWhat.getPosition()));
                    Log.e("focus---------------", String.valueOf(position));

            }
        };});
        //7.在onCreatViewHolder中注册子项视图的两个监听器；1
        viewHolder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int position = viewHolder.getLayoutPosition();
                if(position>=0) {
                        TodoWhat todoWhat = new TodoWhat();
                        todoWhat.setTitle(viewHolder.editText.getText().toString());
                        todoWhat.setPosition(position);
                        marr.set(position, todoWhat);


                        //todoWhat.updateAll("position=?",String.valueOf(todoWhat.getPosition()));
                        Log.e("focus---------------", String.valueOf(position));



                }

            }
        });
        viewHolder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = viewHolder.getLayoutPosition();//10.这里改成getLayoutPosition（）就返回了正常的index值；
                if(position>-1)
                {DataSupport.deleteAll(TodoWhat.class,"position = ?",String.valueOf(marr.get(position).getPosition()));
                    marr.remove(position);
                    notifyItemRemoved(position);}//11.这个函数解决了del按键不能使用的bug!;
                //marr.get(position).updateAll("position==?",String.valueOf(position));
            }
        });


        //8.记得返回一个ViewHolder对象哈
        return viewHolder;
    }
        //9.给控件属性添加内容；
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //给viewHolder对象添加属性，这里只有editText需要添加；
        //因为子视图属性都存放进了viewHolder，所以只能通过viewHolder对象来访问到这些属性；
        viewHolder.editText.setText(marr.get(position).getTitle());


    }










}
