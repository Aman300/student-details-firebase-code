package com.example.test123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Viewdetails extends AppCompatActivity {

    TextView editText1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Student");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetails);

        editText1 = findViewById(R.id.student_details);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                long number_of_student = snapshot.getChildrenCount();
                String student_data = " ";
                Map<String,Object> s = (Map<String, Object>) snapshot.getValue();
                List<Student> student = new ArrayList<>();
                for(Map.Entry<String,Object> entry:s.entrySet()){
                    Map s2 = (Map) entry.getValue();
                    Long temp_roll = (Long) s2.get("rollno");
                    Long temp_mark = (Long) s2.get("marks");
                    String temp_name = (String) s2.get("name");
                   Student s1 = new Student(temp_roll,temp_name,temp_mark);
                   student_data += s1.toString()+"\n";
                //    Toast.makeText(getApplicationContext(),"Total data : "+s1,Toast.LENGTH_LONG).show();
                }
               // Toast.makeText(getApplicationContext(),"Total data : "+number_of_student,Toast.LENGTH_LONG).show();
                System.out.println(number_of_student);
                editText1.setText(student_data);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });

    }
}