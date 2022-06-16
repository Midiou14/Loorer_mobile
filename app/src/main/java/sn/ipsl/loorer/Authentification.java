package sn.ipsl.loorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentification extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB=new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(Authentification.this, "Remplir les champs vides", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)) {
                        Boolean checkuser= DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Authentification.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Authentification.this, "L'inscription a échoué", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(Authentification.this, "Ce compte existe déjà", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Authentification.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}