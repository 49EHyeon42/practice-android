package dev.ehyeon.androidexampleapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dev.ehyeon.androidexampleapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Inject
    UserViewModelFactory userViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EHyeonComponent component = ((EHyeonApplication) getApplication()).getComponent();
        component.inject(this);

        UserViewModel userViewModel = new ViewModelProvider(this, userViewModelFactory)
                .get(UserViewModel.class);

        CustomAdapter adapter = new CustomAdapter(this, userViewModel.findAllUser());

        binding.listView.setAdapter(adapter);

        userViewModel.findAllUserToLiveData().observe(this, users -> {
            adapter.updateList(users);
            adapter.notifyDataSetChanged();
        });

        binding.button.setOnClickListener(view -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            View dialogView = View.inflate(this, R.layout.activity_main_customdialog, null);

            dialogBuilder.setView(dialogView);

            dialogBuilder.setPositiveButton("확인", (dialog, which) -> {
                EditText etEmail = dialogView.findViewById(R.id.etEmail);
                EditText etName = dialogView.findViewById(R.id.etName);

                userViewModel.saveUser(new UserDto(etEmail.getText().toString(), etName.getText().toString()));
            });

            dialogBuilder.setNegativeButton("취소", null);

            dialogBuilder.show();
        });
    }
}
