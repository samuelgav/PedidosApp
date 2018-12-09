package gob.pe.pedidosapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import gob.pe.pedidosapp.Model.User;
import gob.pe.pedidosapp.Common.Common;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword=(MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone=(MaterialEditText)findViewById(R.id.edtPhone);
        btnSingIn=(Button) findViewById(R.id.btnSingIn);

        //Iniciar Base de datos
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog=new ProgressDialog(SignIn.this);
                dialog.setMessage("Por favor espere...");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // comprobar si el usuario no existe en la base de datos
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //Obtener informacion de usuario
                            dialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                {
                                    Intent intent=new Intent(SignIn.this,Home.class);
                                    Common.currentUser=user;
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignIn.this, "Eror al registrar!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "Usuario no existe en base de datos!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}

