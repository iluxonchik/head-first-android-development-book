package com.hfad.starbuzz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DrinkActivity extends ActionBarActivity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (int)getIntent().getExtras().get(EXTRA_DRINKNO);

        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(Drink.drinks[drinkNo].getImageResourceId());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(Drink.drinks[drinkNo].getDescription());

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(Drink.drinks[drinkNo].getName());
    }

}
