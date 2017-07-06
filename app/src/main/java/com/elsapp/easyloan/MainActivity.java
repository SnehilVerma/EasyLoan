package com.elsapp.easyloan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

import com.elsapp.easyloan.Adapter.Chat_Adapter;
import com.elsapp.easyloan.Utility.SessionManager;
import com.elsapp.easyloan.model.Message;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Context context;
    ArrayList<Message> chats;
    int topflag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        if(SessionManager.getStringFromPreferences(context,"loantype").equals("Vehicle")) {
            toolbar.setTitle("Vehicle Loan");
        }
        else
        toolbar.setTitle("Home Loan");
        setSupportActionBar(toolbar);


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        chats=prepareChat();
        



        recyclerView.setAdapter(new Chat_Adapter(context,chats,layoutManager));






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public ArrayList<Message> prepareChat(){
        chats=new ArrayList<>();
        Message m=new Message();

        m.setMessage("Hey There");
        m.setRank("top");
        m.setType("bot");
        m.setId(0);
        chats.add(m);


        m=new Message();
        m.setMessage("Welcome to Easy Solutions");
        m.setRank("follow");
        m.setType("bot");
        m.setId(1);

        chats.add(m);

        m=new Message();
        m.setMessage("Choose a city");
        m.setRank("follow");
        m.setType("bot");
        m.setId(2);
        chats.add(m);



        return chats;




    }



    //CUSTOM CLASS TO ANIMATE EVERY RECYCLER ITEM AS AND WHEN IT IS ADDED IN THE DATA SET.
    //IMPORTANT.
    public void launchAnimation(final RecyclerView recycler,final int position) {

        recycler.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recycler.getViewTreeObserver().removeOnPreDrawListener(this);

                int count=0;
                for (int i = position; i < recycler.getChildCount(); i++) {
                    View v = recycler.getChildAt(i);

                        v.setAlpha(0.0f);
                        v.animate().alpha(1.0f)
                                .setDuration(400)
                                .setStartDelay(i * 100)
                                .start();

                }

                return true;
            }
        });
    }


    public RecyclerView getRecyclerView(){
        return recyclerView;


    }
}
