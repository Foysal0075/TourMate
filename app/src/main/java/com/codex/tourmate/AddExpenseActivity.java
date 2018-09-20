package com.codex.tourmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {

    EditText expenseDetailsEt, expenseAmountEt;
    Button addExpenseButton;
    String currentDateTime;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        expenseDetailsEt = findViewById(R.id.expense_details_edit);
        expenseAmountEt = findViewById(R.id.expense_amount_edit);
        addExpenseButton = findViewById(R.id.addExpense_button);

        calendar = Calendar.getInstance();
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = expenseDetailsEt.getText().toString();
                String am = expenseAmountEt.getText().toString();

                if (!TextUtils.isEmpty(details) && !TextUtils.isEmpty(am)){
                    Double amount =Double.valueOf(am) ;
                    currentDateTime = getDateTime();
                    //Do the Firebase Code here



                }

            }
        });
    }

    public String getDateTime(){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd MMM yyyy   HH:mm aa");
        return  simpleDateFormat.format(calendar.getTime());

    }


}
