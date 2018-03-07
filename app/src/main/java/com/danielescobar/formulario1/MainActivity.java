package com.danielescobar.formulario1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText date, eMostrar, eNombre, eCorreo, ePassword1, ePassword2;
    DatePickerDialog datePickerDialog;
    int mYear, mMonth, mDay;
    String sexo, Nombre, Pasatiempo, ciudad, correo, password1, password2;
    CheckBox cNadar, cCorrer, cCine;
    Spinner sCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date = (EditText) findViewById(R.id.date);
        ePassword1 = (EditText) findViewById(R.id.eContraseña1);
        ePassword2 = (EditText) findViewById(R.id.eContraseña2);
        eMostrar = (EditText) findViewById(R.id.eMostrar);
        eNombre = (EditText) findViewById(R.id.eNombre);
        eCorreo = (EditText) findViewById(R.id.eCorreo);
        cNadar = findViewById(R.id.cNadar);
        cCorrer = findViewById(R.id.cCorrer);
        cCine = findViewById(R.id.cCine);
        sCiudad = findViewById(R.id.sciudad);
        sexo = "Masculio";
        sCiudad.setAdapter(adapter);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR); // current year
                mMonth = c.get(Calendar.MONTH); // current month
                mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                                mYear = year;
                                mDay = dayOfMonth;
                                mMonth = monthOfYear;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        if (id == R.id.rMasculino) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }


    }

    public void onCheckBoxClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cNadar:
                if (cNadar.isChecked()) {
                    Pasatiempo = "Nadar";
                }
                break;
            case R.id.cCine:
                if (cCine.isChecked()) {
                    Pasatiempo = "Cine";
                }
                break;
            case R.id.cCorrer:
                if (cCorrer.isChecked()) {
                    Pasatiempo = "Correr";
                }
                break;

        }

    }

    public void onButtonClicked(View view) {
        password1 = ePassword1.getText().toString();
        password2 = ePassword2.getText().toString();
        if (!Objects.equals(password1, password2)) {
            Toast.makeText(this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            ePassword1.getText().clear();
            ePassword2.getText().clear();
        }
        else if(eNombre.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar el nombre",
                    Toast.LENGTH_SHORT).show();
        }
        else if(ePassword1.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar una contraseña",
                    Toast.LENGTH_SHORT).show();
        }
        else if(eCorreo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar un correo electronico",
                    Toast.LENGTH_SHORT).show();
        }
        else if(mDay==0){
            Toast.makeText(this, "Debes ingresar tu fecha de Nacimieto",
                    Toast.LENGTH_SHORT).show();
        }
        else if(Objects.equals(Pasatiempo, "null"))
        Toast.makeText(this, "Debes ingresar un pasatiempo",
                Toast.LENGTH_SHORT).show();


        else {
            Nombre = eNombre.getText().toString();
            correo = eCorreo.getText().toString();
            ciudad = sCiudad.getSelectedItem().toString();
            eMostrar.setText("Nombre: " + Nombre + "\n"
                    + "correo: " + correo + "\n"
                    + "Sexo: " + sexo + "\n"
                    + "Fecha de Nacimiento: " + mDay + "/" + mMonth + "/" + mYear + "\n"
                    + "Lugar de Nacimiento: " + ciudad + "\n"
                    + "Pasatiempos: " + Pasatiempo + "\n");
        }
    }


}