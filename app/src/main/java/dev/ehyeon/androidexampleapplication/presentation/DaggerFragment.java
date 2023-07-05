package dev.ehyeon.androidexampleapplication.presentation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import dev.ehyeon.androidexampleapplication.R;
import dev.ehyeon.androidexampleapplication.data.user.UserDto;
import dev.ehyeon.androidexampleapplication.databinding.FragmentDaggerBinding;

public class DaggerFragment extends Fragment {

    @Inject
    public UserViewModelFactory userViewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDaggerBinding binding = FragmentDaggerBinding.inflate(inflater);

        UserViewModel userViewModel = new ViewModelProvider(this, userViewModelFactory)
                .get(UserViewModel.class);

        CustomAdapter adapter = new CustomAdapter(userViewModel.findAllUser());

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1)); // 구분선

        userViewModel.findAllUserToLiveData().observe(getViewLifecycleOwner(), adapter::updateList);

        binding.button.setOnClickListener(view -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

            View dialogView = View.inflate(getContext(), R.layout.activity_main_customdialog, null);

            dialogBuilder.setView(dialogView);

            dialogBuilder.setPositiveButton("확인", (dialog, which) -> {
                EditText etEmail = dialogView.findViewById(R.id.etEmail);
                EditText etName = dialogView.findViewById(R.id.etName);

                userViewModel.saveUser(new UserDto(etEmail.getText().toString(), etName.getText().toString()));
            });

            dialogBuilder.setNegativeButton("취소", null);

            dialogBuilder.show();
        });

        return binding.getRoot();
    }
}
