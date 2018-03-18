package com.example.zerlin.dialoghomework;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private TextView textView;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initCalender();
        setListener();
    }

    private void initCalender() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);

        }

    private void initView(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
    }

    private void setListener(){
        MyListener myListener = new MyListener();
        button1.setOnClickListener(myListener);
        button2.setOnClickListener(myListener);
        button3.setOnClickListener(myListener);
    }

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button1:
                    showDateDialog();
                    break;
                case R.id.button2:
                    showSingleDialog();
                    break;
                case R.id.button3:
                    showMultiDialog();
                    break;
            }
        }
    }

    private void showDateDialog(){
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String time = i + "年" + (i1 + 1) + "月" + i2 + "日";
                textView.setText(time);
           }
       },year,month,day).show();
    }

    private void showSingleDialog(){
        final String[] item = {"未闻花名","宇宙よりも遠い場所",
                "龙王的工作", "刀剑神域","没有黄段子的无聊世界","和酒子歌"};
        final int[] index = {0};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.blue)
                .setTitle("请选择你喜欢的动漫")
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        index[0] = i;
                    }
                    })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //注意i不一样了
                        String string = "您喜欢的动漫是"
                                +item[index[0]];
                        textView.setText(string);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {

                    }
                });
                builder.show();
    }

    private void showMultiDialog(){
        final String[] games = {"鬼泣","虐杀原形","侠盗飞车","逆战",
                "英雄联盟","尼尔:机械纪元","使命召唤","绝地求生","穿越火线"};
        final boolean[] booleans = {false,false,false,false,false,false,false,false,false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.dwlp)
                .setTitle("あなたが好きなゲームを選んでください！")
                .setMultiChoiceItems(games, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder("您喜欢的游戏有：");
                        for(int j = 0; j < games.length; j ++ ) {
                            if(booleans[j]) stringBuilder.append(games[j]).append(" ");
                        }
                        textView.setText(stringBuilder.toString());
                    }
                })
                .setNegativeButton("取消",null);
                builder.show();
    }
}
