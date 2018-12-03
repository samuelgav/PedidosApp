package gob.pe.pedidosapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author: samuelgav
 * @version: 02/12/2018
 */
public class MainActivity extends AppCompatActivity {

    Button btnSingIn, btnSingUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSingIn=(Button) findViewById(R.id.btnSingIn);
        btnSingUp=(Button) findViewById(R.id.btnSingUp);

        txtSlogan=(TextView) findViewById(R.id.txtSlogan);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singIn=new Intent(MainActivity.this,SignIn.class);
                startActivity(singIn);
            }
        });

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singUp=new Intent(MainActivity.this,SignUp.class);
                startActivity(singUp);
            }
        });
    }
}
