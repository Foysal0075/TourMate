package com.codex.tourmate;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codex.tourmate.R;
import com.codex.tourmate.event_class.EventInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {

    EditText destinationEt, budgetEt;
    TextView fromDate, toDate;
    String fDate, tDate,destination;
    Double budget;
    Button createEventBtn;

    Calendar calendar =Calendar.getInstance();
    DatePickerDialog datePickerDialog;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference rootref,eventRef;

    List<EventInfo> infoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        destinationEt = findViewById(R.id.destination_edit);
        budgetEt = findViewById(R.id.budget_edit);
        fromDate = findViewById(R.id.from_date_edit);
        toDate =  findViewById(R.id.to_date_edit);
        createEventBtn = findViewById(R.id.createEventBtn);
        rootref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user =auth.getCurrentUser();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference eventRef = reference.child("user").child(user.getUid()).child("event");
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                infoList.clear();
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    EventInfo info = d.getValue(EventInfo.class);
                    Toast.makeText(AddEventActivity.this, info.getEventDestination()+" "+user.getUid(), Toast.LENGTH_LONG).show();
                    infoList.add(info);

                }
                Toast.makeText(AddEventActivity.this, infoList.get(0).getEventDestination(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(AddEventActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFDate();
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTDate();
            }
        });
        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = destinationEt.getText().toString();
                String budgett = budgetEt.getText().toString();

                if (!TextUtils.isEmpty(destination) && !TextUtils.isEmpty(budgett) && !TextUtils.isEmpty(fDate) && !TextUtils.isEmpty(tDate) &&!TextUtils.isEmpty(budgett)){
                    //budget = Double.valueOf(budgett);
                    String key = rootref.child("user").child(user.getUid()).push().getKey();
                    EventInfo eventInfo = new EventInfo(fDate,tDate,budgett,destination,key);
                    rootref.child("user").child(user.getUid()).child("event").child(key).setValue(eventInfo);

                    Toast.makeText(AddEventActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                }
                Toast.makeText(AddEventActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getFDate(){
        datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fDate = dayOfMonth+"."+month+"."+year;
                fromDate.setText(fDate);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void getTDate(){
        datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tDate = dayOfMonth+"."+month+"."+year;
                toDate.setText(tDate);

            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


}
