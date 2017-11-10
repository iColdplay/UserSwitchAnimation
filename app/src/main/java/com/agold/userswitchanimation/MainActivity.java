package com.agold.userswitchanimation;

import android.content.Context;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public AVLoadingIndicatorView indicatorView;
    final Context mContext = this;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicatorView = (AVLoadingIndicatorView) findViewById(R.id.indicator);
        indicatorView.setIndicator("BallScaleMultipleIndicator");
        UserManager um = (UserManager)this.getSystemService(Context.USER_SERVICE);
//        um.getUserName();
//        android.util.Log.i("ly20171106", "show user infomation um.getUserName()-->" + um.getUserName());
//        android.util.Log.i("ly20171106", "show user infomation um.toString()-->" + um.toString());
//        android.util.Log.i("ly20171106", "show user infomation um.getUserProfiles()-->" + um.getUserProfiles());
        if(!um.isSystemUser()) {
            Toast.makeText(this, "show user infomation um.getUserName()-->" + um.getUserName(), Toast.LENGTH_SHORT).show();
        }
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, getString(R.string.app_name) , Toast.LENGTH_SHORT).show();
            }
        });
    }
}