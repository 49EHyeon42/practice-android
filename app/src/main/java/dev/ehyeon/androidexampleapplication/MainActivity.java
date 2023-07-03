package dev.ehyeon.androidexampleapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

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

        CustomAdapter adapter = new CustomAdapter(userViewModel.findAllUser());

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, 1)); // 구분선

        userViewModel.findAllUserToLiveData().observe(this, adapter::updateList);

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
