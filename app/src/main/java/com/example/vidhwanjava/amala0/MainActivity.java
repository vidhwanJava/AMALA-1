package com.example.vidhwanjava.amala0;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    ListView listView2;
    ListView listView3;




     long totalScrollTime;
    final int scrollPeriod = 80; // every 20 ms scroll will happened. smaller values for smoother
    final int heightToScroll = 2;
    final int negHeightToScroll = -2;// will be scrolled to 20 px every time. smaller values for smoother scrolling

    String code;
    String hopapatient;
    String scanimage;
    int lengthOfJason;
    List<String> codeArray;
    List<String> hopapatientArray;
    List<String> scanimageArray;
    String words;
    ArrayAdapter<String> firstListAdapter;
    ArrayAdapter<String> secondListAdapter;
    ArrayAdapter<String> thirdListAdapter;
    List<Integer> codeParse;
    long offset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1=findViewById(R.id.waitingList);
        listView2=findViewById(R.id.proceedList);
        listView3=findViewById(R.id.dispatchList);




       /* int listViewSize = listView1.getCount();
        String listv=""+listViewSize;
        Toast.makeText(getApplicationContext(),listv,Toast.LENGTH_SHORT).show();*/






        // Calls the function getJsonFile to Get the json file from the link http://122.15.159.161:4422/webservice/scanimage/all
        new getJsonFile().execute();









    }



    // Function to set data from the json to the listviews. It cannot be written in onCreate, thus this function.
public void useArrayAdapter(){
    firstListAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, codeArray);

    listView1.setAdapter(firstListAdapter);

    secondListAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hopapatientArray);

    listView2.setAdapter(secondListAdapter);

    thirdListAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scanimageArray);

    listView3.setAdapter(thirdListAdapter);





}


    public  class getJsonFile extends AsyncTask<Void, Void, Void>{



        @Override
        protected Void doInBackground(Void... params){

            try {

                //Document doc =Jsoup.connect("http://122.15.159.161:4422/webservice/scanimage/all").ignoreContentType(true).get();
                Document doc =Jsoup.connect("http://testforamala.atwebpages.com").ignoreContentType(true).get();
                words=doc.text();


            } catch (IOException e) {
                    words=e.toString();
                e.printStackTrace();


            }

            return null;
        }


        @Override

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

             //show toast

            JsonParser();//parse the json file that is downloaed from the method getjsonfile
            toSetScrollTime();
            showText();
           // Collections.sort(codeArray); activate if need to sort the json 'code' acording to the string
            useArrayAdapter();// set the listview with the parsed json file using a adapter


            Runnable mynewRunnable = new Runnable() {
                @Override
                public void run() {
                    while(true){
                        scroller1();
                        scroller2();
                        scroller3();

                        SystemClock.sleep(offset);
                    }}
            };


            Thread myThread = new Thread(mynewRunnable);
            myThread.start();
        }
    }


    public void JsonParser(){


        codeArray = new ArrayList<>();
        //codeArray.sort();

        hopapatientArray= new ArrayList<>();
        scanimageArray= new ArrayList<>();
        try {
            JSONArray jsonResponse = new JSONArray(words);

            for (int i = 0; i < jsonResponse.length(); i++) {
                JSONObject dataReceivedObject = jsonResponse.getJSONObject(i);
                code = dataReceivedObject.getString("code");
                codeArray.add(code);
                hopapatient=dataReceivedObject.getString("hopapatient");
                hopapatientArray.add(hopapatient);
                scanimage=dataReceivedObject.getString("scanimage");
                scanimageArray.add(scanimage);

                //Toast.makeText(this,code,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,hopapatient,Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {

            e.printStackTrace();
            Log.d("error",e.toString());
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }





    public void showText(){
        Toast.makeText(this,words,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Length"+lengthOfJason,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"totalScrollTime"+totalScrollTime,Toast.LENGTH_LONG).show();
    }

    public void toSetScrollTime(){
        lengthOfJason=codeArray.size();
        totalScrollTime=(1300*lengthOfJason);
        offset=(long)(2*totalScrollTime);
        //TODO if a minimum no. of objects are not present, skip scrolling
        //TODO implement Refresh cycle

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
