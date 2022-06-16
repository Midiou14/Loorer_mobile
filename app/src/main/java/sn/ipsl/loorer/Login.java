package sn.ipsl.loorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    EditText username;
    EditText password;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.signin);
        DB= new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(Login.this, "Veuillez remplir tous les champs svp", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.checkusernamepassword(user,pass);
                    if (checkuserpass ==true) {
                        Toast.makeText(Login.this, "Connexion effectuée avec succès", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),PageAccueil.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "La tentative de connexion a échoué", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}