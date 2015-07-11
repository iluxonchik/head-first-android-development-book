package com.hfad.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    /* onCreate() gets called when the activity is started */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    // Call onSendMessage() when the button is clicked
    public void onSendMessage(View view) {
        EditText editText = (EditText)findViewById(R.id.message);
        String messageText = editText.getText().toString();

        EditText subject = (EditText)findViewById(R.id.subject);
        String subjectText = subject.getText().toString();


        Intent intent = new Intent(Intent.ACTION_SEND); // create and implicit intent with "send" action
        intent.setType("text/plain"); // set MIME type to text plain
        intent.putExtra(Intent.EXTRA_TEXT, messageText); // put the extra to send
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectText);

        // force app selection screen
        /*
            Android asks the user which app he wants to run, this time with no "Always Use This App"
            option and returns an explicit intent associated with the user-chosen activity.

            The explicit intent is stored in "explicitChooserIntent" var
         */
        Intent explicitChooserIntent = Intent.createChooser(intent, getString(R.string.chooser));
        startActivity(explicitChooserIntent); // tell Android to start the activity specified in the intent
    }
}
