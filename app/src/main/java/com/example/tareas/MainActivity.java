package com.example.tareas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private long selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        EditText editT1 = findViewById(R.id.editT1);
        CheckBox chkbox1 = findViewById(R.id.chkbox1);
        CalendarView calendar1 = findViewById(R.id.calendar1);
        ImageButton imgButton1 = findViewById(R.id.imgButton1);

        /* Selección de fecha / Actualización de fecha */
        calendar1.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDay = new java.util.GregorianCalendar(year, month, dayOfMonth).getTimeInMillis();
        });

        /* Función del botón */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = chkbox1.isChecked();
                String textFromEditText = editT1.getText().toString();
                // Formatear la fecha seleccionada
                @SuppressLint("SimpleDateFormat")
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String dateString = sdf.format(new java.util.Date(selectedDay));

                // Obtener Tiempo
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                @SuppressLint("DefaultLocale")
                String message = isChecked + " " + textFromEditText + " " + dateString + " " + String.format("%02d:%02d:%02d", hour, minute, second);

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        /* Moverme de una página a otra*/
        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}