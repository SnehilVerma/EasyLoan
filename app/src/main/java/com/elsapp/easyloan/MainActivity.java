package com.elsapp.easyloan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.elsapp.easyloan.Adapter.Chat_Adapter;
import com.elsapp.easyloan.model.Message;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Context context;
    ArrayList<Message> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=this;


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        chats=prepareChat();
        recyclerView.setAdapter(new Chat_Adapter(context,chats));





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


        /*
        m=new Message();
        m.setMessage("I live in Mumbai");
        m.setRank("top");
        m.setType("user");
        m.setId(3);
        chats.add(m);
        m=new Message();
        m.setMessage("Nice to meet you");
        m.setRank("follow");
        m.setType("user");
        m.setId(4);
        chats.add(m);
        */

        return chats;




    }
}
