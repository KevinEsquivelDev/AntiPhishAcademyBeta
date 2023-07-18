package com.auroraguatemala.antiphishacademy.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaUnoCurso;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CursoFragment extends Fragment {
    private ImageView imageView;
    private ImageView iconoTrofeo;
    private TextView tituloProgreso;
    private TextView textoProgreso;
    private ProgressBar barraProgreso;
    private Button btnContinuar;
    public static final String TEST_PREFERENCE = "TestPreference";
    public static final String TEST_APPROVED_KEY = "TestApproved";

    public static final String TEST_PREFERENCE2 = "TestPreference2";
    public static final String TEST_APPROVED_KEY2 = "TestApproved2";

    public static final String TEST_PREFERENCE3 = "TestPreference3";
    public static final String TEST_APPROVED_KEY3 = "TestApproved3";

    public static final String TEST_PREFERENCE4 = "TestPreference4";
    public static final String TEST_APPROVED_KEY4 = "TestApproved4";

    public static final String TEST_PREFERENCE5 = "TestPreference5";
    public static final String TEST_APPROVED_KEY5 = "TestApproved5";

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences2;
    private SharedPreferences sharedPreferences3;
    private SharedPreferences sharedPreferences4;
    private SharedPreferences sharedPreferences5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.curso_fragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = requireActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_curso));
        }

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        imageView = rootView.findViewById(R.id.imageView);
        iconoTrofeo = rootView.findViewById(R.id.icono_trofeo);
        tituloProgreso = rootView.findViewById(R.id.titulo_progreso);
        textoProgreso = rootView.findViewById(R.id.texto_progreso);
        barraProgreso = rootView.findViewById(R.id.barra_progreso);
        btnContinuar = rootView.findViewById(R.id.btnContinuar);

        sharedPreferences = requireContext().getSharedPreferences(TEST_PREFERENCE, Context.MODE_PRIVATE);
        sharedPreferences2 = requireContext().getSharedPreferences(TEST_PREFERENCE2, Context.MODE_PRIVATE);
        sharedPreferences3 = requireContext().getSharedPreferences(TEST_PREFERENCE3, Context.MODE_PRIVATE);
        sharedPreferences4 = requireContext().getSharedPreferences(TEST_PREFERENCE4, Context.MODE_PRIVATE);
        sharedPreferences5 = requireContext().getSharedPreferences(TEST_PREFERENCE5, Context.MODE_PRIVATE);

        boolean testApproved = sharedPreferences.getBoolean(TEST_APPROVED_KEY, false);
        boolean testApproved2 = sharedPreferences2.getBoolean(TEST_APPROVED_KEY2, false);
        boolean testApproved3 = sharedPreferences3.getBoolean(TEST_APPROVED_KEY3, false);
        boolean testApproved4 = sharedPreferences4.getBoolean(TEST_APPROVED_KEY4, false);
        boolean testApproved5 = sharedPreferences5.getBoolean(TEST_APPROVED_KEY5, false);

        updateTestStatus(testApproved, testApproved2,testApproved3, testApproved4,testApproved5);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtapaUnoCurso fragment = new EtapaUnoCurso();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(TEST_APPROVED_KEY, true);
                editor.apply();
            }
        });

        Button profileIcon = rootView.findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.main_container, profileFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    private void updateTestStatus(boolean testApproved, boolean testApproved2, boolean testApproved3, boolean testApproved4, boolean testApproved5) {
        int percentage = sharedPreferences.getInt("TestPercentage", 0);
        int percentage2 = sharedPreferences2.getInt("TestPercentage2", 0);
        int percentage3 = sharedPreferences3.getInt("TestPercentage3", 0);
        int percentage4 = sharedPreferences4.getInt("TestPercentage4", 0);
        int percentage5 = sharedPreferences5.getInt("TestPercentage5", 0);

        if (testApproved && percentage >= 80) {
            if (testApproved2 && percentage2 >= 80) {
                if (testApproved3 && percentage3 >= 80) {
                    if (testApproved4 && percentage4 >= 80) {
                        if (testApproved5 && percentage5 >= 80) {
                            barraProgreso.setProgress(5);
                            textoProgreso.setText("5/5");
                        } else {
                            barraProgreso.setProgress(4);
                            textoProgreso.setText("4/5");
                        }
                    } else {
                        barraProgreso.setProgress(3);
                        textoProgreso.setText("3/5");
                    }
                } else {
                    barraProgreso.setProgress(2);
                    textoProgreso.setText("2/5");
                }
            } else {
                barraProgreso.setProgress(1);
                textoProgreso.setText("1/5");
            }
        } else if (!testApproved && percentage >= 80 && testApproved2 && percentage2 >= 80 && testApproved3 && percentage3 >= 80 && testApproved4 && percentage4 >= 80 && testApproved5 && percentage5 >= 80) {
            barraProgreso.setProgress(0);
            textoProgreso.setText("0/5");
        } else if (testApproved && percentage >= 80 && !testApproved2 && percentage2 >= 80 && testApproved3 && percentage3 >= 80 && testApproved4 && percentage4 >= 80 && testApproved5 && percentage5 >= 80) {
            barraProgreso.setProgress(1);
            textoProgreso.setText("1/5");
        } else if (testApproved && percentage >= 80 && testApproved2 && percentage2 >= 80 && !testApproved3 && percentage3 >= 80 && testApproved4 && percentage4 >= 80 && testApproved5 && percentage5 >= 80) {
            barraProgreso.setProgress(2);
            textoProgreso.setText("2/5");
        } else if (testApproved && percentage >= 80 && testApproved2 && percentage2 >= 80 && testApproved3 && percentage3 >= 80 && !testApproved4 && percentage4 >= 80 && testApproved5 && percentage5 >= 80) {
            barraProgreso.setProgress(2);
            textoProgreso.setText("3/5");
        } else {
            barraProgreso.setProgress(0);
            textoProgreso.setText("0/5");
        }

    }

}
