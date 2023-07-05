package dev.ehyeon.androidexampleapplication.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.stream.Stream;

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

        // init fragment
        DaggerFragment daggerFragment = new DaggerFragment();
        Retrofit2Fragment retrofit2Fragment = new Retrofit2Fragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, daggerFragment, MainFragmentType.of(R.id.item_dagger).getTag())
                .add(R.id.container, retrofit2Fragment, MainFragmentType.of(R.id.item_retrofit2).getTag())
                .show(daggerFragment)
                .hide(retrofit2Fragment).commit();

        binding.bottomNavigation.setOnItemSelectedListener(item -> updateFragmentById(item.getItemId()));
    }

    private boolean updateFragmentById(int id) {
        Stream.of(MainFragmentType.values())
                .forEach(mainFragmentType -> {
                    if (mainFragmentType.getId() == id) {
                        getSupportFragmentManager().beginTransaction()
                                .show(getFragmentById(mainFragmentType.getId())).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction()
                                .hide(getFragmentById(mainFragmentType.getId())).commit();
                    }
                });
        return true;
    }

    private Fragment getFragmentById(int id) {
        return getSupportFragmentManager().findFragmentByTag(MainFragmentType.of(id).getTag());
    }
}
