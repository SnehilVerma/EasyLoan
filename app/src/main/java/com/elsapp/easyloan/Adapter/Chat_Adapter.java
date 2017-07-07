package com.elsapp.easyloan.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.elsapp.easyloan.MainActivity;
import com.elsapp.easyloan.R;
import com.elsapp.easyloan.Utility.SessionManager;
import com.elsapp.easyloan.model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static java.security.AccessController.getContext;

/**
 * Created by snehil on 29/6/17.
 */

//MAIN CLASS FOR QEC FUNCTIONALITY.
//PLEASE KEEP A TABLE READY FOR THE NUMBER AND COUNT OF MESSAGES WHICH ARE TO BE USED.
//THE FLOW IS BASED ON THE ID OF EACH MESSAGE WHICH IS NOTHING BUT THE INORDER NUMBERING OF MESSAGES STARTING FRM 0.

public class Chat_Adapter extends RecyclerView.Adapter<Chat_Adapter.ViewHolder> {

    private ArrayList<Message> chats;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    Calendar myCalendar = Calendar.getInstance();   //for DOB.
    ArrayList<String> city_names;       // FOR CITY POPUP.

    int flag=0;         //for DOM.
    int gflag=0;

    int branchSize=0;
    String[] bikelist={"Activa","Avenger","Aviator","Passion","Dio","Pleasure","Splendor","Maestro","Scooty Pep","Pulsar"};
    String[] carlist={"WagonR","Eon","i30","Dzire","Xcent","Kwid","Creta","Elite i20"};

    //FOR ANIMATION
    private final static int FADE_DURATION = 1000;
    private int lastposition=-1;

    public Chat_Adapter(Context context, ArrayList<Message> items,RecyclerView.LayoutManager linearLayoutManager) {
        this.chats = items;
        this.context = context;
        this.layoutManager=linearLayoutManager;

    }


    public void addMessage(Message message){
        chats.add(message);
        notifyItemInserted(chats.size());
        layoutManager.scrollToPosition(chats.size()-1); // shift the focus to the new item which is added.

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


        /*
        //FETCH CURRENT RECYCLER VIEW INSTANCE WITH UPDATE STATUS AND PASS IT TO LAUNCH ANIMATION


        RecyclerView recyclerView=((MainActivity)context).getRecyclerView();
        if(position>lastposition) {
            ((MainActivity) context).launchAnimation(recyclerView, position);
            lastposition=position;
        }
        */
        //the above code ensures animation takes place only for new set of chats and if previous data is edited animation
        //doesnt re-render everything.







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
                                city_names =new ArrayList<>(Arrays.asList("Kanpur","Lucknow","Bengaluru","Patna","Surat","Kota","Jaipur","Pune","Panaji"));
                                final Dialog dialog2=new Dialog(context);
                                dialog2.setContentView(R.layout.popup_other_cities);
                                dialog2.setTitle("Choose a city");
                                //dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                                final AutoCompleteTextView city = (AutoCompleteTextView) dialog2.findViewById(R.id.cityedit);
                                ArrayAdapter<String> adapterone = new ArrayAdapter<String>(context,R.layout.city_item,R.id.list_content, city_names);
                                //ArrayAdapter<String> adapter2=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,city_names);
                                ListView cities=(ListView)dialog2.findViewById(R.id.List);


                                cities.setAdapter(adapterone);
                                city.setAdapter(adapterone);
                                city.setDropDownHeight(0);

                                dialog2.show();

                                cities.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        String res=adapterView.getAdapter().getItem(i).toString();

                                        Message m=new Message();
                                        m.setMessage("I live in "+res);
                                        m.setType("user");
                                        m.setRank("top");
                                        m.setId(3);
                                        addMessage(m);

                                        notifyDataSetChanged();
                                        dialog2.dismiss();
                                        dialog.dismiss();
                                        launchGender();
                                    }
                                });




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
                                /*
                                        Message m=new Message();
                                        m.setMessage("I live in others");
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();
                                */

                                city_names =new ArrayList<>(Arrays.asList("Kanpur","Lucknow","Bengaluru","Patna","Surat","Kota","Jaipur","Pune","Panaji"));
                                final Dialog dialog2=new Dialog(context);
                                dialog2.setContentView(R.layout.popup_other_cities);
                                dialog2.setTitle("Choose a city");
                                final AutoCompleteTextView city = (AutoCompleteTextView) dialog2.findViewById(R.id.cityedit);
                                final ArrayAdapter<String> adapterone = new ArrayAdapter<String>(context,R.layout.city_item,R.id.list_content, city_names);
                                ListView cities=(ListView)dialog2.findViewById(R.id.List);
                                city.setDropDownHeight(0);
                                //final City_Adapter adapter=new City_Adapter(getContext(),R.layout.name_view,city_names);
                                cities.setAdapter(adapterone);
                                city.setAdapter(adapterone);
                                dialog2.show();



                                cities.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        String res=adapterView.getAdapter().getItem(i).toString();
                                        Message m=new Message();
                                        m.setMessage("I live in "+res);
                                        m.setType("user");
                                        m.setRank("top");
                                        chats.set(3,m);
                                        notifyDataSetChanged();
                                        dialog2.dismiss();
                                        dialog.dismiss();

                                    }
                                });



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

                if(chats.get(position).getId()==8){
                    if(chats.size()==9){
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.popup_vehicle_select);
                        dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        ImageView im1, im2;

                        im1=(ImageView)dialog.findViewById(R.id.car);
                        im2=(ImageView)dialog.findViewById(R.id.bike);

                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I want a four wheeler");
                                SessionManager.putStringInPreferences(context,"Car","vehicle_type");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(9);
                                addMessage(m);
                                dialog.dismiss();
                                launchCarType();

                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I want a two wheeler");
                                SessionManager.putStringInPreferences(context,"Bike","vehicle_type");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(9);
                                addMessage(m);
                                dialog.dismiss();
                                launchBikeType();



                            }
                        });

                        dialog.show();


                    }else if(chats.size()>9){
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.popup_vehicle_select);
                        dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        ImageView im1, im2;

                        im1=(ImageView)dialog.findViewById(R.id.car);
                        im2=(ImageView)dialog.findViewById(R.id.bike);

                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")) {

                                    //SUBLISTING. IMPORTANT TO REMOVE ALREADY INCLUDED FRAGMENTS INCASE OF BRANCH CHANGE.
                                    chats.subList(9,chats.size()).clear();
                                    notifyDataSetChanged();

                                    Message m = new Message();
                                    m.setMessage("I want a four wheeler");
                                    SessionManager.putStringInPreferences(context,"Car","vehicle_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(9);
                                    addMessage(m);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    launchCarType();

                                }
                                else{
                                    dialog.dismiss();

                                }


                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car")) {
                                    chats.subList(9, chats.size()).clear();
                                    notifyDataSetChanged();

                                    Message m = new Message();
                                    m.setMessage("I want a two wheeler");
                                    SessionManager.putStringInPreferences(context,"Bike","vehicle_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(9);
                                    addMessage(m);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    launchBikeType();

                                }
                                else{
                                    dialog.dismiss();

                                }


                            }
                        });

                        dialog.show();



                    }


                }



                if(chats.get(position).getId()==10){        // CHOOSE NEW /OLD VEHICLE.
                    if(chats.size()==11){

                        if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car")) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.popup_car_type);
                            dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            ImageView im1, im2;

                            im1 = (ImageView) dialog.findViewById(R.id.newcar);
                            im2 = (ImageView) dialog.findViewById(R.id.oldcar);


                            im1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Message m = new Message();
                                    m.setMessage("A new car!");
                                    SessionManager.putStringInPreferences(context, "New", "car_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(11);
                                    addMessage(m);
                                    dialog.dismiss();
                                    launchCarPref();

                                }
                            });
                            im2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Message m = new Message();
                                    m.setMessage("A second hand car");
                                    SessionManager.putStringInPreferences(context, "Old", "car_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(11);
                                    addMessage(m);
                                    dialog.dismiss();
                                    launchDOM();

                                }
                            });


                            dialog.show();
                        }else{

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.popup_bike_type);
                            dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            ImageView im1, im2;

                            im1 = (ImageView) dialog.findViewById(R.id.newbike);
                            im2 = (ImageView) dialog.findViewById(R.id.oldbike);


                            im1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Message m = new Message();
                                    m.setMessage("A new bike!");
                                    SessionManager.putStringInPreferences(context, "New", "bike_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(11);
                                    addMessage(m);
                                    dialog.dismiss();
                                    launchBikePref();

                                }
                            });
                            im2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Message m = new Message();
                                    m.setMessage("A second hand bike");
                                    SessionManager.putStringInPreferences(context, "Old", "bike_type");
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(11);
                                    addMessage(m);
                                    dialog.dismiss();
                                    launchBikePref();

                                }
                            });


                            dialog.show();
                        }

                    }else if(chats.size()>11){

                        if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car")) {

                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.popup_car_type);
                            dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            ImageView im1, im2;

                            im1 = (ImageView) dialog.findViewById(R.id.newcar);
                            im2 = (ImageView) dialog.findViewById(R.id.oldcar);

                            im2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (SessionManager.getStringFromPreferences(context, "car_type").equals("New")) {
                                        //SUBLISTING. IMPORTANT TO REMOVE ALREADY INCLUDED FRAGMENTS INCASE OF BRANCH CHANGE.
                                        chats.subList(11, chats.size()).clear();

                                        notifyDataSetChanged();
                                        Message m = new Message();
                                        m.setMessage("A second hand car");
                                        SessionManager.putStringInPreferences(context, "Old", "car_type");
                                        m.setType("user");
                                        m.setRank("top");
                                        addMessage(m);
                                        m.setId(11);
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                        launchDOM();
                                    } else {
                                        dialog.dismiss();
                                    }


                                }
                            });

                            im1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (SessionManager.getStringFromPreferences(context, "car_type").equals("Old")) {
                                        chats.subList(11, chats.size()).clear();
                                        notifyDataSetChanged();

                                        Message m = new Message();
                                        m.setMessage("A new car");
                                        SessionManager.putStringInPreferences(context, "New", "car_type");
                                        m.setType("user");
                                        m.setRank("top");
                                        m.setId(11);
                                        addMessage(m);
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                        launchCarPref();

                                    } else {
                                        dialog.dismiss();

                                    }


                                }
                            });

                            dialog.show();
                        }else{


                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.popup_bike_type);
                            dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            ImageView im1, im2;

                            im1 = (ImageView) dialog.findViewById(R.id.newbike);
                            im2 = (ImageView) dialog.findViewById(R.id.oldbike);

                            im1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (SessionManager.getStringFromPreferences(context, "bike_type").equals("New")) {
                                        //SUBLISTING. IMPORTANT TO REMOVE ALREADY INCLUDED FRAGMENTS INCASE OF BRANCH CHANGE.
                                        chats.subList(11, chats.size()).clear();
                                        notifyDataSetChanged();
                                        Message m = new Message();
                                        m.setMessage("A second hand bike");
                                        SessionManager.putStringInPreferences(context, "Old", "bike_type");
                                        m.setType("user");
                                        m.setRank("top");
                                        addMessage(m);
                                        m.setId(11);
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                        launchBikePref();
                                    } else {
                                        dialog.dismiss();
                                    }


                                }
                            });

                            im2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (SessionManager.getStringFromPreferences(context, "bike_type").equals("Old")) {
                                        chats.subList(11, chats.size()).clear();
                                        notifyDataSetChanged();

                                        Message m = new Message();
                                        m.setMessage("A new bike");
                                        SessionManager.putStringInPreferences(context, "New", "bike_type");
                                        m.setType("user");
                                        m.setRank("top");
                                        m.setId(11);
                                        addMessage(m);
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                        launchBikePref();

                                    } else {
                                        dialog.dismiss();

                                    }


                                }
                            });

                            dialog.show();


                        }
                    }



                }


                if(chats.get(position).getId()==12){           //OLD CAR BRANCH INCLUDES DOM POPUP WHICH WILL MERGE
                                                                //WITH THE NEW CAR BRANCH.
                        if(chats.size()==13){

                            //Toast.makeText(context,"here",Toast.LENGTH_SHORT).show();
                            final Dialog dialog2=new Dialog(context);
                            dialog2.setContentView(R.layout.popup_dom);
                            dialog2.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            Spinner spin_month=(Spinner)dialog2.findViewById(R.id.spin_month);
                            Spinner spin_year=(Spinner)dialog2.findViewById(R.id.spin_year);

                            String[] items = new String[]{"No Selection","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                            ArrayAdapter<String> d_adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,items);
                            spin_month.setAdapter(d_adapter);
                            String[] items2 = new String[]{"No Selection","2017","2016","2015","2014"};
                            ArrayAdapter<String> d_adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,items2);
                            spin_year.setAdapter(d_adapter2);

                            dialog2.show();

                            spin_month.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    String check=adapterView.getSelectedItem().toString();
                                    if(check.equals("No Selection")) {
                                        flag = 0;
                                    }
                                    else{
                                        flag=1;

                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


                            spin_year.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    //   year.setText(adapterView.getSelectedItem().toString());
                                    String check = adapterView.getSelectedItem().toString();
                                    if (check.equals("No Selection")) {
                                        gflag=0;

                                    }
                                    else{
                                        if (flag == 0) {
                                            Toast.makeText(context, "Choose month first", Toast.LENGTH_SHORT).show();
                                        } else {
                                            gflag = 1;


                                        }

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


                            Button b=(Button)dialog2.findViewById(R.id.b2);
                            b.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Message m = new Message();
                                    m.setMessage("Date of manufacturing of vehicle is "+ "temp");   //REPLACE TEMP BY
                                    //VALUE SELECTED FROM SPINNER.

                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(13);
                                    addMessage(m);
                                    dialog2.dismiss();
                                    launchCarPref();


                                }
                            });

                        }
                    }




                if(chats.get(position).getId()==14){            // NEW/OLD BIKE or NEW CAR

                    if (chats.size()== 13) {

                        Integer[] res = getThumbs();
                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.popup_pref_car_bike);
                        dialog2.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        GridView gridView = (GridView) dialog2.findViewById(R.id.gridview);
                        Animation animation = AnimationUtils.loadAnimation(context, R.anim.grid_item_anim);
                        final GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
                        gridView.setLayoutAnimation(controller);
                        gridView.setAdapter(new Car_Bike_Grid(context, res));
                        dialog2.show();

                        gridView.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")) {
                                    String res = bikelist[i];
                                    Message m = new Message();
                                    m.setMessage("Preferred two wheeler is " + res);   //REPLACE TEMP BY
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(15);
                                    addMessage(m);
                                    dialog2.dismiss();
                                    launchCostOfVehicle();
                                }else{
                                    String res = carlist[i];
                                    Message m = new Message();
                                    m.setMessage("Preferred four wheeler is " + res);   //REPLACE TEMP BY
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(15);
                                    addMessage(m);
                                    dialog2.dismiss();
                                    launchCostOfVehicle();

                                }


                            }
                        });


                    }else if(chats.size()==15 && SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car") && SessionManager.getStringFromPreferences(context,"car_type").equals("Old")){     //OLD CAR BRANCH

                        Integer[] res = getThumbs();
                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.popup_pref_car_bike);
                        dialog2.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        GridView gridView = (GridView) dialog2.findViewById(R.id.gridview);
                        Animation animation = AnimationUtils.loadAnimation(context, R.anim.grid_item_anim);
                        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
                        gridView.setLayoutAnimation(controller);
                        gridView.setAdapter(new Car_Bike_Grid(context, res));

                        gridView.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")) {
                                    String res = bikelist[i];
                                    Message m = new Message();
                                    m.setMessage("Preferred two wheeler is " + res);   //REPLACE TEMP BY
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(15);
                                    addMessage(m);
                                    dialog2.dismiss();
                                    launchCostOfVehicle();
                                }else{

                                    String res = carlist[i];
                                    Message m = new Message();
                                    m.setMessage("Preferred four wheeler is " + res);   //REPLACE TEMP BY
                                    m.setType("user");
                                    m.setRank("top");
                                    m.setId(15);
                                    addMessage(m);
                                    dialog2.dismiss();
                                    launchCostOfVehicle();

                                }


                            }


                        });

                        dialog2.show();



                    }
                    /*
                    else if(chats.size()>13){
                        //edit new car  OR  new/old bike.
                        if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car")) {
                            if (SessionManager.getStringFromPreferences(context, "car_type").equals("New")) {
                                //Toast.makeText(context,"New car branch edit",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")){
                                //Toast.makeText(context,"Bike branch edit",Toast.LENGTH_SHORT).show();

                            }

                            }
                        }*/



                    else if(chats.size()>13){

                        Toast.makeText(context,"EDIT CHOICE",Toast.LENGTH_SHORT).show();



                        if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Car")) {
                            if (SessionManager.getStringFromPreferences(context, "car_type").equals("Old")) {
                                Toast.makeText(context, "Old car branch edit", Toast.LENGTH_SHORT).show();
                                //edit old car
                            }
                            else     if(SessionManager.getStringFromPreferences(context,"car_type").equals("New")){
                                Toast.makeText(context, "New car branch edit", Toast.LENGTH_SHORT).show();
                                //edit new car
                            }

                        }

                        else if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")){
                            Toast.makeText(context, "Bike branch edit", Toast.LENGTH_SHORT).show();
                            //edit bike.
                            }
                        }



                    }



                if(chats.get(position).getId()==16){

                    if(chats.size()==17 || chats.size()==15){


                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.popup_cost_vehicle);
                        dialog2.getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        final EditText editText=(EditText)dialog2.findViewById(R.id.editText);
                        Button button=(Button)dialog2.findViewById(R.id.button);

                        button.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String res=editText.getText().toString();
                                Message m = new Message();
                                m.setMessage("Cost is " + res);   //REPLACE TEMP BY
                                m.setType("user");
                                m.setRank("top");
                                m.setId(17);
                                addMessage(m);
                                dialog2.dismiss();
                                launchSelectCategory();
                            }
                        });


                        dialog2.show();


                    }







                }


                if(chats.get(position).getId()==18){

                    final Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.popup_select_category);
                    dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    ImageView im1,im2,im3,im4,im5,im6,im7;



                    im1=(ImageView)dialog.findViewById(R.id.im1);
                    im2=(ImageView)dialog.findViewById(R.id.im2);
                    im3=(ImageView)dialog.findViewById(R.id.im3);
                    im4=(ImageView)dialog.findViewById(R.id.im4);
                    im5=(ImageView)dialog.findViewById(R.id.im5);
                    im6=(ImageView)dialog.findViewById(R.id.im6);
                    im7=(ImageView)dialog.findViewById(R.id.im7);


                    if(chats.size()==17||chats.size()==19){

                        im1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Retired Non-Pensioner");
                                SessionManager.putStringInPreferences(context,"employment_type","Retired_NP");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });
                        im2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I get a Salary");
                                SessionManager.putStringInPreferences(context,"employment_type","Salaried");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });
                        im3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Salaried Professional");
                                SessionManager.putStringInPreferences(context,"employment_type","SalariedProf");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();


                            }
                        });
                        im4.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Retired Pensioner");
                                SessionManager.putStringInPreferences(context,"employment_type","Retired_P");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(3);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });
                        im5.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Self Employed Professional");
                                SessionManager.putStringInPreferences(context,"employment_type","Self_Employed_P");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });
                        im6.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am Self Employed");
                                SessionManager.putStringInPreferences(context,"employment_type","Self_Employed");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });
                        im7.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Message m = new Message();
                                m.setMessage("I am a Home Maker");
                                SessionManager.putStringInPreferences(context,"employment_type","HomeMaker");
                                m.setType("user");
                                m.setRank("top");
                                m.setId(19);
                                addMessage(m);
                                launchIncomeDetails();
                                dialog.dismiss();

                            }
                        });





                    dialog.show();

                    }



                }


                if(chats.get(position).getId()==20){
                    //INPUT INCOME DETAILS CORRESPONDING TO THE CHOSEN CATEGORY.

                    if(chats.size()==21||chats.size()==19){

                        final Dialog dialog=new Dialog(context);
                        String employ=SessionManager.getStringFromPreferences(context,"employment_type");
                        if(employ.equals("Retired_NP")){
                            dialog.setContentView(R.layout.popup_retired_np);

                        }else if(employ.equals("Salaried")){
                            dialog.setContentView(R.layout.popup_salaried);

                        }else if(employ.equals("SalariedProf")){

                            dialog.setContentView(R.layout.popup_salariedprof);
                        }else if(employ.equals("Retired_P")){
                            dialog.setContentView(R.layout.popup_retired_p);

                        }else if(employ.equals("Self_Employed_P")){
                            dialog.setContentView(R.layout.popup_self_employed_p);
                        }else if(employ.equals("Self_Employed")){
                            dialog.setContentView(R.layout.popup_self_employed);

                        }else{
                            dialog.setContentView(R.layout.popup_homemaker);
                        }

                        dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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


    public void launchCarType(){
        Message m=new Message();
        m.setMessage("Tap here to choose a car type");
        m.setType("bot");
        m.setRank("top");
        m.setId(10);
        addMessage(m);      //PUSHBACK TO DATA SET.
    }

    public void launchBikeType(){
        Message m=new Message();
        m.setMessage("Tap here to choose a bike type");
        m.setType("bot");
        m.setRank("top");
        m.setId(10);
        addMessage(m);      //PUSHBACK TO DATA SET.
    }


    public void launchSecondCarPref(){          //FOR SECONDARY CAR BRANCH.
        Message m=new Message();
        m.setMessage("Tap here to select a car!");
        m.setType("bot");
        m.setRank("top");
        m.setId(14);
        addMessage(m);      //PUSHBACK TO DATA SET.
    }


    public void launchCarPref(){
        Message m=new Message();
        m.setMessage("Tap here to select a car!");
        m.setType("bot");
        m.setRank("top");
        m.setId(14);
        addMessage(m);      //PUSHBACK TO DATA SET.

    }

    public void launchBikePref(){
        Message m=new Message();
        m.setMessage("Tap here to select a bike!");
        m.setType("bot");
        m.setRank("top");
        m.setId(14);
        addMessage(m);      //PUSHBACK TO DATA SET.

    }

    public void launchDOM(){
        Message m=new Message();
        m.setMessage("Choose the date of manufacturing of the vehicle");
        m.setType("bot");
        m.setRank("top");
        m.setId(12);           //todo: branching logic yet to be finalized.
        branchSize=2;           // for in between branched messages.
        addMessage(m);      //PUSHBACK TO DATA SET.


    }

    public void launchCostOfVehicle(){
        Message m=new Message();
        m.setMessage("Tap to enter the cost");
        m.setType("bot");
        m.setRank("top");
        m.setId(16);           //todo: branching logic yet to be finalized.
        addMessage(m);


    }


    public void launchSelectCategory(){
        Message m=new Message();
        m.setMessage("Tap here to choose your income source");
        m.setType("bot");
        m.setRank("top");
        m.setId(18);           //todo: branching logic yet to be finalized.
        addMessage(m);  //pushback



    }



    public void launchIncomeDetails(){

        Message m=new Message();
        m.setMessage("Tap here to enter your income details");
        m.setType("bot");
        m.setRank("top");
        m.setId(20);           //todo: branching logic yet to be finalized.
        addMessage(m);  //pushback

    }


    public Integer[] getThumbs(){

        if(SessionManager.getStringFromPreferences(context,"vehicle_type").equals("Bike")) {

            Integer[] thumbs = {
                    R.drawable.activa, R.drawable.avenger,
                    R.drawable.aviator, R.drawable.passion,
                    R.drawable.dio, R.drawable.pleasure,
                    R.drawable.splendor, R.drawable.maestro,
                    R.drawable.scooty_pep, R.drawable.pulsar,

            };

            return thumbs;
        }
        else{
            Integer[] thumbs = {
                    R.drawable.wagonr, R.drawable.eon,
                    R.drawable.i30, R.drawable.dzire,
                    R.drawable.xcent, R.drawable.kwid,
                    R.drawable.creta,R.drawable.elite_i20};

            return thumbs;
        }
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


    //ANIMATION FUNCTIONS. CHANGE THE DESIRED ANIM. AS PER REQUIREMENTS.

    //FADE ANIMATION.
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }


    //SCALE ANIMATION.
    private void setScaleAnimation(View view,int position) {

        if (position > lastposition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(FADE_DURATION);
            view.startAnimation(anim);
            lastposition = position;

        }
    }

    //SLIDE ANIMATION
    private void setSlideAnimation(View view,int position) {

        if (position > lastposition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastposition = position;

        }
    }








}
