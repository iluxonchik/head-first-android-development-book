package com.hfad.starbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.CharacterCodingException;
import java.util.Currency;


public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            // Runs on main thread, therefore can access/modify UI
            CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            // Runs in the background thread, therefore can't access/modify UI
            int drinkNo = drinks[0];
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);

            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("DRINK", drinkValues, "_id = ?", new String[]{Integer.toString(drinkNo)});
                db.close();

                return  true; // success!
            } catch (SQLiteException e) {
                return false; // fail!
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            // Runs on main thread, therefore can access/modify UI
            // Gets passed what doInBackground returns
            if(!success) {
                Toast toast = Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (int)getIntent().getExtras().get(EXTRA_DRINKNO);

        // Create cursor
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK", new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkNo)},
                    null, null, null);
            // Move to the first record in the cursor
            if (cursor.moveToFirst()) {
                // Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String  descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1); // check if the fave column is true

                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);

                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onFavoriteClicked(View view) {
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);

        new UpdateDrinkTask().execute(drinkNo);
    }

}
