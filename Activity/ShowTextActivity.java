package wilx.android.wtb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import wilx.android.wtb.R;

public class ShowTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show_text);

        ((TextView) findViewById(R.id.show_text_textview)).setText(getIntent().getStringExtra("Extra"));
    }
}
