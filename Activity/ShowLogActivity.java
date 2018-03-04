package wilx.android.wtb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import wilx.android.wtb.R;

public class ShowLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_showlog);

        ((TextView) findViewById(R.id.showlog_textView)).setText(getIntent().getStringExtra("Extra"));
    }
}
