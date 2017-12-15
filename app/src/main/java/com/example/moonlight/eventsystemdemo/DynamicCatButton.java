package com.example.moonlight.eventsystemdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicCatButton extends AppCompatActivity {
    String c1,catprice;
    int a,capri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_cat_button);
        c1 = getIntent().getStringExtra("cat");
        catprice = getIntent().getStringExtra("catprice");
        //capri=Integer.parseInt(catprice);

        a = Integer.parseInt(c1);
        if (a == 1) {


            for (int i = 1; i <= a; ++i) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.oe);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);


                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.activity_category, null);
                //View myView = rowView.findViewById( R.id.ss );
                Button b = (Button) rowView.findViewById(R.id.firstC);
                TextView t1 = (TextView) rowView.findViewById(R.id.cat1prize);
                b.setText(i + " " + "Category");
                String[] namesList = catprice.split(",");
                final String name1 = namesList[0];
                t1.setText("---------"+"$."+name1);
                layout.addView(rowView);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                        myinten.putExtra("costs1", name1);
                        startActivity(myinten);
                    }
                });
            }

        }
       else  if (a == 2) {


            for (int i = 1; i <= a; ++i) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.oe);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);


                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.activity_category, null);
                //View myView = rowView.findViewById( R.id.ss );
                Button b = (Button) rowView.findViewById(R.id.firstC);
                TextView t1 = (TextView) rowView.findViewById(R.id.cat1prize);
                b.setText(i + " " + "Category");
                String[] namesList = catprice.split(",");
                final  String   name1 = namesList[0];
               final String name2 = namesList[1];
                if(i==1) {
                    t1.setText("-----"+name1);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name1);
                            startActivity(myinten);

                        }
                    });
                }
                 else if(i==2) {
                    t1.setText("------"+name2);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name2);
                            startActivity(myinten);

                        }
                    });
                }

                layout.addView(rowView);
            }

        }
         else if (a == 3) {


            for (int i = 1; i <= a; ++i) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.oe);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);


                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.activity_category, null);
                //View myView = rowView.findViewById( R.id.ss );
                Button b = (Button) rowView.findViewById(R.id.firstC);
                TextView t1 = (TextView) rowView.findViewById(R.id.cat1prize);
                b.setText(i + " " + "Category");
                String[] namesList = catprice.split(",");
                final String name1 = namesList[0];
                final String name2 = namesList[1];
                final String name3 = namesList[2];
                if (i == 1) {
                    t1.setText("---------" + "$." + name1);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name1);
                            startActivity(myinten);

                        }
                    });
                } else if (i == 2)
                {

            t1.setText("---------" + "$." + name2);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                    myinten.putExtra("costs1", name2);
                    startActivity(myinten);

                }
            });
        }
                else if(i==3) {
                    t1.setText("---------" + "$." + name3);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name3);
                            startActivity(myinten);

                        }
                    });
                }

                layout.addView(rowView);
            }

        }
        else if (a == 4) {


            for (int i = 1; i <= a; ++i) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.oe);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);


                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.activity_category, null);
                //View myView = rowView.findViewById( R.id.ss );
                Button b = (Button) rowView.findViewById(R.id.firstC);
                TextView t1 = (TextView) rowView.findViewById(R.id.cat1prize);
                b.setText(i + " " + "Category");
                String[] namesList = catprice.split(",");
                final String name1 = namesList[0];
                final String name2 = namesList[1];
                final String name3 = namesList[2];
                final String name4 = namesList[3];
                if (i == 1)
                {
                    t1.setText("---------" + "$." + name1);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                        myinten.putExtra("costs1", name1);
                        startActivity(myinten);

                    }
                });
            }
                else if(i==2) {
                    t1.setText("---------" + "$." + name2);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name2);
                            startActivity(myinten);

                        }
                    });
                }
                else if(i==3) {
                    t1.setText("---------" + "$." + name3);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name3);
                            startActivity(myinten);

                        }
                    });
                }
                else if(i==4) {
                    t1.setText("---------" + "$." + name4);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name4);
                            startActivity(myinten);

                        }
                    });
                }

                layout.addView(rowView);
            }

        }
        else if (a == 5) {


            for (int i = 1; i <= a; ++i) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.oe);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);


                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.activity_category, null);
                //View myView = rowView.findViewById( R.id.ss );
                Button b = (Button) rowView.findViewById(R.id.firstC);
                TextView t1 = (TextView) rowView.findViewById(R.id.cat1prize);
                b.setText(i + " " + "Category");
                String[] namesList = catprice.split(",");
             final   String name1 = namesList[0];
             final   String name2 = namesList[1];
             final   String name3 = namesList[2];
            final    String name4 = namesList[3];
           final    String name5 = namesList[4];
                if(i==1) {
                    t1.setText("---------" + "$." + name1);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name1);
                            startActivity(myinten);

                        }
                    });
                }

                else if(i==2) {
                    t1.setText("---------" + "$." + name2);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name2);
                            startActivity(myinten);

                        }
                    });
                }
                else if(i==3) {
                    t1.setText("---------" + "$." + name3);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name3);
                            startActivity(myinten);

                        }
                    });
                }
                else if(i==4) {
                    t1.setText("---------" + "$." + name4);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name4);
                            startActivity(myinten);

                        }
                    });
                }
                else if(i==5) {
                    t1.setText("---------" + "$." + name5);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                            myinten.putExtra("costs1", name5);
                            startActivity(myinten);

                        }
                    });
                }


                layout.addView(rowView);
            }

        }

    }
    /*public  void click(View view)
    {
        bClick();
    }

    private void bClick() {

                Intent myinten = new Intent(DynamicCatButton.this, Booking1.class);
                myinten.putExtra("costs1", name1);
                startActivity(myinten);


    }*/
}
