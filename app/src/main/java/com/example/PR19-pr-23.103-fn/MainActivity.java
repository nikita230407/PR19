package com.example.pz19belyaevvadim;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvDateTime;
    private Button btnDatePicker, btnTimePicker, btnCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов
        tvDateTime = findViewById(R.id.tvDateTime);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);

        // 1. Устанавливаем текущую дату и время при запуске
        setCurrentDateTime();

        // 2. Обработчик кнопки Даты
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // 3. Обработчик кнопки Времени
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // 4. Обработчик кнопки обычного диалога (Задание 3)
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomAlertDialog();
            }
        });
    }

    // Метод для отображения текущей даты и времени
    private void setCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Месяцы начинаются с 0
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String currentDateTime = String.format("Дата: %02d.%02d.%d\nВремя: %02d:%02d",
                day, month, year, hour, minute);
        tvDateTime.setText(currentDateTime);
    }

    // Диалог выбора даты
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Обновляем TextView с новой датой, сохраняя старое время или сбрасывая
                    String selectedDate = String.format("Выбрана дата: %02d.%02d.%d",
                            dayOfMonth, monthOfYear + 1, year1);
                    tvDateTime.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    // Диалог выбора времени
    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    String selectedTime = String.format("Выбрано время: %02d:%02d",
                            hourOfDay, minute1);
                    tvDateTime.setText(selectedTime);
                }, hour, minute, true); // true - 24-часовой формат

        timePickerDialog.show();
    }

    // Задание 3: Обычный AlertDialog
    private void showCustomAlertDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Информация")
                .setMessage("Это пример диалогового окна.\nПередача данных работает!")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Действие при нажатии OK
                    tvDateTime.setText("Диалог закрыт через OK");
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    // Действие при отмене
                    dialog.cancel();
                });

        builder.create().show();
    }
}