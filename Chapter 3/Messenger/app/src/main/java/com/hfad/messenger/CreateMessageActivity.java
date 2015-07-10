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
        Intent intent = new Intent(this, ReceiveMessageActivity.class);

        String messageText = editText.getText().toString();
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, messageText);

        startActivity(intent); // tell Android to start the activity specified in the intent
    }
}
