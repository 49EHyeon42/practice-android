package dev.ehyeon.androidexampleapplication.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dev.ehyeon.androidexampleapplication.EHyeonApplication;
import dev.ehyeon.androidexampleapplication.R;
import dev.ehyeon.androidexampleapplication.databinding.ActivityMainBinding;
import dev.ehyeon.androidexampleapplication.di.EHyeonComponent;

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

//        UserViewModel userViewModel = new ViewModelProvider(this, userViewModelFactory)
//                .get(UserViewModel.class);
//
//        CustomAdapter adapter = new CustomAdapter(userViewModel.findAllUser());
//
//        binding.recyclerView.setAdapter(adapter);
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, 1)); // 구분선
//
//        userViewModel.findAllUserToLiveData().observe(this, adapter::updateList);
//
//        binding.button.setOnClickListener(view -> {
//            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//
//            View dialogView = View.inflate(this, R.layout.activity_main_customdialog, null);
//
//            dialogBuilder.setView(dialogView);
//
//            dialogBuilder.setPositiveButton("확인", (dialog, which) -> {
//                EditText etEmail = dialogView.findViewById(R.id.etEmail);
//                EditText etName = dialogView.findViewById(R.id.etName);
//
//                userViewModel.saveUser(new UserDto(etEmail.getText().toString(), etName.getText().toString()));
//            });
//
//            dialogBuilder.setNegativeButton("취소", null);
//
//            dialogBuilder.show();
//        });

        DaggerFragment daggerFragment = new DaggerFragment();
        Retrofit2Fragment retrofit2Fragment = new Retrofit2Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.containers, daggerFragment).commit();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_dagger) {
                if (daggerFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().show(daggerFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.containers, daggerFragment).commit();
                }

                getSupportFragmentManager().beginTransaction().hide(retrofit2Fragment).commit();

                return true;
            } else if (item.getItemId() == R.id.menu_retrofit2) {
                if (retrofit2Fragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().show(retrofit2Fragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.containers, retrofit2Fragment).commit();
                }

                getSupportFragmentManager().beginTransaction().hide(daggerFragment).commit();

                return true;
            }

            return false;
        });
    }
}
