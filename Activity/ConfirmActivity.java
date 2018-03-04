package wilx.android.wtb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import wilx.android.wtb.R;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm);

        Intent i = getIntent();
        ((TextView)findViewById(R.id.confirm_textView)).setText(i.getStringExtra("Message"));
        ((TextView)findViewById(R.id.confirm_button1)).setText(i.getStringExtra("LeftText"));
        ((TextView)findViewById(R.id.confirm_button2)).setText(i.getStringExtra("RightText"));
    }

    public View button1(View v) {
        finish();
        return v;
    }

    public View button2(View v) {
        setResult(12);
        finish();
        return v;
    }
}
