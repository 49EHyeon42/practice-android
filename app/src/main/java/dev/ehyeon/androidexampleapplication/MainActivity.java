package dev.ehyeon.androidexampleapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.button);

        UserRepository userRepository = new UserRepository();

        // Test
        userRepository.save("Test@Email1.com", "Test1");
        userRepository.save("Test@Email2.com", "Test2");

        listView.setAdapter(new CustomAdapter(this, userRepository));
    }
}
