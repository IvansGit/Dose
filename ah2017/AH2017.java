package damian.ah2017;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.animation.Animator;

public class AH2017 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ah2017);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(AH2017.this, HomeActivity.class);
        startActivity(intent);
    }
}
