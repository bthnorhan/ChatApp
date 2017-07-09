package tr.bthnorhan.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = db.getReference("Chats");
    ArrayList<String> topicList = new ArrayList<>();
    ListView lv_main_topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topicList.add("Yükleniyor.");

        lv_main_topics = (ListView) findViewById(R.id.lv_main_topics);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,topicList);
        lv_main_topics.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topicList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    topicList.add(ds.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv_main_topics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("topic",topicList.get(position));
                startActivity(intent);
            }
        });
    }
}
