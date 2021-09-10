package com.example.test123;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    EditText roll_no,edit_name,marks;
    Button save,show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         roll_no = findViewById(R.id.rollno);
         edit_name  = findViewById(R.id.name);
         marks = findViewById(R.id.mark);
         save = findViewById(R.id.save);
         show = findViewById(R.id.show);

         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                    Student s1 = fetch_student_details();
                    if(s1!=null){
                        myRef = database.getReference().child("Student").child(Long.toString(s1.getRollno()));
                        myRef.setValue(s1);
                        Toast.makeText(getApplicationContext(),"Your data is save succussfully",Toast.LENGTH_LONG).show();
                    }
             }
         });
         show.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent v = new Intent(MainActivity.this,Viewdetails.class);
                 startActivity(v);

             }
         });



    }
    public Student fetch_student_details(){
        String rollno = roll_no.getText().toString();
        String name = edit_name.getText().toString();
        String mark = marks.getText().toString();

        if(rollno==null||rollno.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter rollno",Toast.LENGTH_LONG).show();
            return null;
        }
        if(name==null||name.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_LONG).show();
            return null;
        }
        if(mark==null||mark.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter marks",Toast.LENGTH_LONG).show();
            return null;
        }
        Long student_rollno;
        Long student_marks;
        try {
            student_rollno = Long.parseLong(rollno);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Enter valid rollno",Toast.LENGTH_LONG).show();
            return null;
        }
        try {
            student_marks = Long.parseLong(mark);
            if(student_marks<0||student_marks>100){
                Toast.makeText(getApplicationContext(),"Enter marks between 0 to 100",Toast.LENGTH_LONG).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Enter valid marks",Toast.LENGTH_LONG).show();
            return null;
        }
        Student s1 = new Student(student_rollno,name,student_marks);

        return s1;
    }
}
