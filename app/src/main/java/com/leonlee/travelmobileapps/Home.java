package com.leonlee.travelmobileapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Home extends AppCompatActivity {

    private ImageView iv1, iv2, iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);

        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=1253%2C834%2C3%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2Fe2673f7a001badd2dac4e874ff4a63ea%2F202427659%2F499003003.jpg&client=a1acac3e1b3290917d92&signature=2a403fcf202531618b5d6e6acbc768ccf0d203b0").into(iv1);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=4965%2C3305%2C206%2C0&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F8efe542a6f2c5921cc2e69e2d3b54880%2F204906839%2F497347635.jpg&client=a1acac3e1b3290917d92&signature=f1e0be04017814d31f7bb7165eadf037e03840b5").into(iv2);
        Glide.with(this).load("https://o.aolcdn.com/images/dims?crop=5616%2C3738%2C0%2C2&quality=85&format=jpg&resize=640%2C426&image_uri=http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F4a5e2cb4f4288b84b2248103a3a56cb%2F203524460%2Fstock-photo-breathtaking-view-in-the-plitvice-lakes-national-park-croatia-65988727.jpg&client=a1acac3e1b3290917d92&signature=82935e63c9640c54d08c8cf0635e29a0a61d8693").into(iv3);
    }
}
