package com.example.vidhwanjava.amala0;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    ListView listView2;
    ListView listView3;




    final long totalScrollTime = 15000;
    final int scrollPeriod = 35; // every 20 ms scroll will happened. smaller values for smoother
    final int heightToScroll = 3;
    final int negHeightToScroll = -3;// will be scrolled to 20 px every time. smaller values for smoother scrolling





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1=findViewById(R.id.waitingList);
        listView2=findViewById(R.id.proceedList);
        listView3=findViewById(R.id.dispatchList);



        int listViewSize = listView1.getCount();
        String listv=""+listViewSize;
        Toast.makeText(getApplicationContext(),listv,Toast.LENGTH_SHORT).show();





        Runnable mynewRunnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                scroller1();
                scroller2();
                scroller3();

                SystemClock.sleep(3*totalScrollTime);
            }}
        };


        Thread myThread = new Thread(mynewRunnable);
        myThread.start();




    }



    public void scroller1(){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                listView1.post(new Runnable() {

                    @Override

                    public void run() {

                        new CountDownTimer(totalScrollTime, scrollPeriod) {

                            public void onTick(long millisUntilFinished) {
                                listView1.scrollBy(0, heightToScroll);

                            }


                            public void onFinish() {
                                listView1.post(new Runnable() {

                                    @Override
                                    public void run() {

                                        new CountDownTimer(totalScrollTime, scrollPeriod) {
                                            public void onTick(long millisUntilFinished) {
                                                listView1.scrollBy(0, negHeightToScroll);

                                            }

                                            public void onFinish() {

                                                //you can add code for restarting timer here


                                            }
                                        }.start();
                                    }
                                });
                                //you can add code for restarting timer here


                            }
                        }.start();
                    }
                });




            }
        };


        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }





    public void scroller2(){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                listView2.post(new Runnable() {

                    @Override

                    public void run() {

                        new CountDownTimer(totalScrollTime, scrollPeriod) {

                            public void onTick(long millisUntilFinished) {
                                listView2.scrollBy(0, heightToScroll);

                            }


                            public void onFinish() {
                                listView2.post(new Runnable() {

                                    @Override
                                    public void run() {

                                        new CountDownTimer(totalScrollTime, scrollPeriod) {
                                            public void onTick(long millisUntilFinished) {
                                                listView2.scrollBy(0, negHeightToScroll);

                                            }

                                            public void onFinish() {

                                                //you can add code for restarting timer here


                                            }
                                        }.start();
                                    }
                                });
                                //you can add code for restarting timer here


                            }
                        }.start();
                    }
                });




            }
        };


        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }






    public void scroller3(){
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                listView3.post(new Runnable() {

                    @Override

                    public void run() {

                        new CountDownTimer(totalScrollTime, scrollPeriod) {

                            public void onTick(long millisUntilFinished) {
                                listView3.scrollBy(0, heightToScroll);

                            }


                            public void onFinish() {
                                listView3.post(new Runnable() {

                                    @Override
                                    public void run() {

                                        new CountDownTimer(totalScrollTime, scrollPeriod) {
                                            public void onTick(long millisUntilFinished) {
                                                listView3.scrollBy(0, negHeightToScroll);

                                            }

                                            public void onFinish() {

                                                //you can add code for restarting timer here


                                            }
                                        }.start();
                                    }
                                });
                                //you can add code for restarting timer here


                            }
                        }.start();
                    }
                });




            }
        };


        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }


}
