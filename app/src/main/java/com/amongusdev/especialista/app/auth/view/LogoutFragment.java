package com.amongusdev.especialista.app.auth.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.utils.Utils;

public class LogoutFragment extends Fragment {

    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        Utils.saveValuePreference(getActivity(), "auth", "");
        Utils.goToNextActivityCleanStack(getActivity(), LoginActivity.class, true, null);
        return v;
    }
}