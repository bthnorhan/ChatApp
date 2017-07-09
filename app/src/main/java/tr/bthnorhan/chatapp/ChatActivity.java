package tr.bthnorhan.chatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    String topic;
    FirebaseDatabase db;
    DatabaseReference dbRef;
    FirebaseUser fUser;
    EditText et_chat_message;
    Button btn_chat_send;
    ListView lv_chat_chat;

    ArrayList<Message> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        list.add(new Message("","Yükleniyor.",""));
        fUser = FirebaseAuth.getInstance().getCurrentUser();

        btn_chat_send = (Button) findViewById(R.id.btn_chat_send);
        et_chat_message = (EditText) findViewById(R.id.et_chat_message);
        lv_chat_chat = (ListView) findViewById(R.id.lv_chat_chat);
        lv_chat_chat.setDivider(null);
        final CustomAdapter adapter = new CustomAdapter(this,
                list,
                FirebaseAuth.getInstance().getCurrentUser());
        lv_chat_chat.setAdapter(adapter);

        Bundle bnd = getIntent().getExtras();
        if (bnd != null)
        {
            topic = bnd.getString("topic").toString();
            setTitle(topic);
            db = FirebaseDatabase.getInstance();
            dbRef = db.getReference("Chats/" + topic + "/message");
        }

        btn_chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(new Date());
                dbRef.push().setValue(new Message(fUser.getEmail(),
                        et_chat_message.getText().toString().trim(),
                        time));
                et_chat_message.setText("");
            }
        });

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    list.add(ds.getValue(Message.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
