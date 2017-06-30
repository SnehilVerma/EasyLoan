package com.elsapp.easyloan.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elsapp.easyloan.R;
import com.elsapp.easyloan.model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by snehil on 29/6/17.
 */


//PLEASE KEEP A TABLE READY FOR THE NUMBER AND COUNT OF MESSAGES WHICH ARE TO BE USED.
//THE FLOW IS BASED ON THE ID OF EACH MESSAGE WHICH IS NOTHING BUT THE INORDER NUMBERING OF MESSAGES STARTING FRM 0.

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.ViewHolder> {

    private ArrayList<Message> chats;
    private Context context;
    Calendar myCalendar = Calendar.getInstance();   //for DOB.


    public Chat_Adapter(Context context, ArrayList<Message> items) {
        this.chats = items;
        this.context = context;
    }


    public void addMessage(Message message){
        chats.add(message);
        notifyItemInserted(chats.size());

    }

    @Override
    public Chat_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;

        if(viewType==0){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_app_chat1, parent, false);

        }
        else if(viewType==1){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_app_chat2, parent, false);

        }
        else if(viewType==2){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_chat1, parent, false);
        }
        else {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_chat2, parent, false);

        }


        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final Chat_Adapter.ViewHolder holder, final int position) {

        holder.botmessage.setText(chats.get(position).getMessage());
        holder.linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //FOR CITY.
                if(chats.get(position).getId()==2){

                    final Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.popup_city);
                    dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    ImageView im1,im2,im3,im4,im5;

                    im1=(ImageView)dialog.findViewById(R.id.mumbai);
                    im2=(ImageView)dialog.findViewById(R.id.delhi);
                    im3=(ImageView)dialog.findViewById(R.id.bengaluru);
                    im4=(ImageView)dialog.findViewById(R.id.agra);
                    im5=(ImageView)dialog.findViewById(R.id.others);


                    if(chats.size()==3) {       //FOR CITY.
                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I live in Mumbai");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(3);
                                addMessage(m);
                                dialog.dismiss();
                                launchGender();
                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I live in New Delhi");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(3);
                                addMessage(m);
                                dialog.dismiss();
                                launchGender();
                            }
                        });
                        im3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I live in Bengaluru");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(3);
                                addMessage(m);
                                dialog.dismiss();
                                launchGender();

                            }
                        });
                        im4.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I live in Agra");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(3);
                                addMessage(m);
                                dialog.dismiss();
                                launchGender();
                            }
                        });
                        im5.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I live in others");       //TODO: OTHERS FUNCTIONALITY NOT IMPLEMENTED.
                                m.setType("user");
                                m.setRank("top");
                                addMessage(m);
                                dialog.dismiss();
                                launchGender();
                            }
                        });

                    }else if(chats.size()>3){       // REPLACE ELEMENT IN THE EXISTING LOCATION.
                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                        Message m=new Message();
                                        m.setMessage("I live in Mumbai");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();



                                dialog.dismiss();

                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        Message m=new Message();
                                        m.setMessage("I live in New Delhi");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();


                                dialog.dismiss();
                            }
                        });
                        im3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                        Message m=new Message();
                                        m.setMessage("I live in Bengaluru");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();

                                dialog.dismiss();

                            }
                        });
                        im4.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                        Message m=new Message();
                                        m.setMessage("I live in Agra");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        });
                        im5.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                        Message m=new Message();
                                        m.setMessage("I live in others");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        });




                    }

                    dialog.show();
                }

                //FOR GENDER.
                if(chats.get(position).getId()==4) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.popup_gender);
                    dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    ImageView im1, im2, im3;

                    im1=(ImageView)dialog.findViewById(R.id.male);
                    im2=(ImageView)dialog.findViewById(R.id.female);
                    im3=(ImageView)dialog.findViewById(R.id.others);



                    if(chats.size()==5) {
                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Male");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                addMessage(m);
                                dialog.dismiss();
                                launchDOB();
                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Female");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                addMessage(m);
                                dialog.dismiss();
                                launchDOB();

                            }
                        });
                        im3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("Others");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                addMessage(m);
                                dialog.dismiss();
                                launchDOB();

                            }
                        });

                        dialog.show();
                    }
                    else if(chats.size()>5){        //REPLACE EXISTING GENDER

                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Male");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                chats.set(5,m);
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Female");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                chats.set(5,m);
                                notifyDataSetChanged();
                                dialog.dismiss();


                            }
                        });
                        im3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("Others");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(5);
                                chats.set(5,m);
                                notifyDataSetChanged();
                                dialog.dismiss();


                            }
                        });

                        dialog.show();
                    }
                }


                if(chats.get(position).getId()==6){ //DOB

                    if(chats.size()==7) {

                        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                // TODO Auto-generated method stub

                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, monthOfYear);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                updateLabel();
                            }

                        };


                        DatePickerDialog dialog;
                        dialog = new DatePickerDialog(context, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));
                        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - (long) 5.676e+11 - (long) 3.456e+8 - (long) 8.64e+7);
                        dialog.show();


                    }
                    else if(chats.size()>7){
                        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                // TODO Auto-generated method stub

                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, monthOfYear);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                resetLabel();
                            }

                        };


                        DatePickerDialog dialog;
                        dialog = new DatePickerDialog(context, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));
                        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - (long) 5.676e+11 - (long) 3.456e+8 - (long) 8.64e+7);
                        dialog.show();



                    }
                }


            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        String type=chats.get(position).getType();
        String rank=chats.get(position).getRank();


        if(type.equals("bot") && rank.equals("top")){
            return 0;}
        else if(type.equals("bot") && rank.equals("follow")){
            return 1;}
        else if(type.equals("user") && rank.equals("top")){
            return 2;
            }
        else{
            return 3;
            }
        }



    @Override
    public int getItemCount() {
        return chats.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView botmessage;
        LinearLayout linearLayout;


        public ViewHolder(View view) {
            super(view);
            botmessage = (TextView) view.findViewById(R.id.tv1);
            linearLayout=(LinearLayout)view.findViewById(R.id.linear);
            //tv2 = (TextView) view.findViewById(R.id.t2);
        }
    }


    public void launchGender(){

            Message m = new Message();
            m.setMessage("Nice! Now tap here to select you GENDER");
            m.setType("bot");
            m.setRank("top");
            m.setId(4);
            addMessage(m);      //PUSHBACK TO DATA SET.


    }

    public void launchDOB(){
        Message m=new Message();
        m.setMessage("What is your DOB?");
        m.setType("bot");
        m.setRank("top");
        m.setId(6);
        addMessage(m);      //PUSHBACK TO DATA SET.


    }

    public void launchVehicleType(){
        Message m=new Message();
        m.setMessage("Tap here to choose a vehicle type");
        m.setType("bot");
        m.setRank("top");
        m.setId(8);
        addMessage(m);      //PUSHBACK TO DATA SET.


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Message m=new Message();
        m.setMessage(String.valueOf(sdf.format(myCalendar.getTime())));
        m.setType("user");
        m.setRank("top");
        m.setId(7);
        addMessage(m);
        launchVehicleType();




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void resetLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Message m=new Message();
        m.setMessage(String.valueOf(sdf.format(myCalendar.getTime())));
        m.setType("user");
        m.setRank("top");
        m.setId(7);
        chats.set(7,m);
        notifyDataSetChanged();




    }





}
