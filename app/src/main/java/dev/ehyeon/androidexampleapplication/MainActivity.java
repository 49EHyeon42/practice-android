package dev.ehyeon.androidexampleapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.button);

        UserRepository userRepository = ((EHyeonApplication) getApplication()).getUserRepository();

        // TODO refactor
        adapter = new CustomAdapter(this, userRepository);

        listView.setAdapter(adapter);

        button.setOnClickListener(view -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            View dialogView = View.inflate(this, R.layout.activity_main_customdialog, null);

            dialogBuilder.setView(dialogView);

            dialogBuilder.setPositiveButton("확인", (dialog, which) -> {
                EditText etEmail = dialogView.findViewById(R.id.etEmail);
                EditText etName = dialogView.findViewById(R.id.etName);

                // adapter.saveUser(etEmail.getText().toString(), etName.getText().toString());

                userRepository.save(etEmail.getText().toString(), etName.getText().toString());

                // TODO clear, debug
                List<User> allUser = userRepository.findAllUser();

                for (User u : allUser) {
                    Log.i("Test", u.email);
                }

                // adapter.notifyDataSetChanged();
            });

            dialogBuilder.setNegativeButton("취소", null);

            dialogBuilder.show();
        });
    }
}
