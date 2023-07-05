package dev.ehyeon.androidexampleapplication.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.stream.Stream;

import dev.ehyeon.androidexampleapplication.EHyeonApplication;
import dev.ehyeon.androidexampleapplication.R;
import dev.ehyeon.androidexampleapplication.databinding.ActivityMainBinding;
import dev.ehyeon.androidexampleapplication.di.EHyeonComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initMainFragment();

        binding.bottomNavigation.setOnItemSelectedListener(item -> updateFragmentById(item.getItemId()));
    }

    private void initMainFragment() {
        Fragment daggerFragment = getFragmentById(R.id.item_dagger);
        if (daggerFragment == null) {
            daggerFragment = new DaggerFragment();
        }

        Fragment retrofit2Fragment = getFragmentById(R.id.item_retrofit2);
        if (retrofit2Fragment == null) {
            retrofit2Fragment = new Retrofit2Fragment();
        }

        if (!daggerFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, daggerFragment, MainFragmentType.of(R.id.item_dagger).getTag())
                    .show(daggerFragment).commit();
        }

        if (!retrofit2Fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, retrofit2Fragment, MainFragmentType.of(R.id.item_retrofit2).getTag())
                    .hide(retrofit2Fragment).commit();
        }
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

    public EHyeonComponent getComponent() {
        return ((EHyeonApplication) getApplication()).getComponent();
    }
}
