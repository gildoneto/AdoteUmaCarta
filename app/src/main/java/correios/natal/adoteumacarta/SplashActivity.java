package correios.natal.adoteumacarta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        };

        mHandler = new Handler();

        mHandler.postDelayed(mRunnable, 4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler!= null && mRunnable != null)
        mHandler.removeCallbacks(mRunnable);
    }
}
