package com.Adam.GwEstimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GwEstimatorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSearchRequested();
            }
        });
    }
}