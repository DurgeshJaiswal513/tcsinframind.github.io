package com.example.inframind;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.w3c.dom.Text;
public class ProfileActivity extends AppCompatActivity {
 private FirebaseUser user;
 private DatabaseReference reference;
 private String userID;
 private Button logout;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_profile);
 logout = (Button)findViewById(R.id.signOut);
 logout.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 FirebaseAuth.getInstance().signOut();
 startActivity(new Intent(ProfileActivity.this,MainActivity.class));
 }
 });
 user = FirebaseAuth.getInstance().getCurrentUser();
 reference = FirebaseDatabase.getInstance().getReference("Users");
 userID = user.getUid();
 final TextView greetingTextView = (TextView)findViewById(R.id.greeting);
 final TextView emailTextView = (TextView)findViewById(R.id.emailAddress);
 final TextView bodyTextView = (TextView)findViewById(R.id.bodyTemperature);
 final TextView bloodTextView = (TextView)findViewById(R.id.bloodPressure);
 final TextView respirationTextView =
(TextView)findViewById(R.id.Respiration);
 final TextView glucoseTextView = (TextView)findViewById(R.id.Glucose);
 final TextView heartTextView = (TextView)findViewById(R.id.heartRate);
 final TextView oxygenTextView =
(TextView)findViewById(R.id.oxygenSaturation);
 final TextView electroTextView =
(TextView)findViewById(R.id.electroCardiogram);
 // Now get user data from the database
 reference.child(userID).addListenerForSingleValueEvent(new
ValueEventListener() {
 @Override
 public void onDataChange(@NonNull DataSnapshot snapshot) {
 User userProfile = snapshot.getValue(User.class);
 if(userProfile != null)
 {
 String fullName = userProfile.fullname;
String email = userProfile.email;
String temp = userProfile.bodyTemperature;
String blood = userProfile.bloodPressure;
 String respiration = userProfile.respiration;
String glucose = userProfile.glucose;
String heart = userProfile.heartRate;
String oxygen = userProfile.oxygenSaturation;
String cardiogram = userProfile.electroCardiogram;
 greetingTextView.setText("Welcome, " + fullName);
 emailTextView.setText(email);
bodyTextView.setText(temp);
bloodTextView.setText(blood);
respirationTextView.setText(respiration);
glucoseTextView.setText(glucose);
heartTextView.setText(heart);
oxygenTextView.setText(oxygen);
electroTextView.setText(cardiogram);
 }
 }
 @Override
 public void onCancelled(@NonNull DatabaseError error) {
 Toast.makeText(ProfileActivity.this,"Something went
wrong!",Toast.LENGTH_LONG).show();
 }
 });
 }
}