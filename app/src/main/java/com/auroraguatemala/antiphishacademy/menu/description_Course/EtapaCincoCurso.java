package com.auroraguatemala.antiphishacademy.menu.description_Course;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.PdfViewerFragment;
import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.description_Course.test.TestModel1Fragment;
import com.auroraguatemala.antiphishacademy.menu.description_Course.test.TestModel4Fragment;
import com.auroraguatemala.antiphishacademy.menu.description_Course.test.TestModel5Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EtapaCincoCurso extends Fragment {

    private static final String TEST_PREFERENCE5 = "TestPreference5";
    private static final String TEST_APPROVED_KEY5 = "TestApproved5";

    private TextView testStatus;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.etapa_cinco_curso, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = requireActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_curso));
        }

        // Mostrar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        Button backButton = rootView.findViewById(R.id.back_button);
        testStatus = rootView.findViewById(R.id.test_status2);
        //Button btnContinuar = rootView.findViewById(R.id.btnContinuar);

        sharedPreferences = requireActivity().getSharedPreferences(TEST_PREFERENCE5, Context.MODE_PRIVATE);
        boolean testApproved2 = sharedPreferences.getBoolean(TEST_APPROVED_KEY5, false);

        updateTestStatus(testApproved2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al fragmento InicioFragment con una transición personalizada
                requireActivity().getSupportFragmentManager().popBackStack();
                requireActivity().getSupportFragmentManager().executePendingTransactions();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.main_container, new EtapaCuatroCurso())
                        .commit();
                BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });


        CardView cardView = rootView.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfUrl = "http://copilotweb2023.great-site.net/Guia_Phishing_5.pdf";
                PdfViewerFragment pdfViewerFragment = new PdfViewerFragment();

                Bundle bundle = new Bundle();
                bundle.putString("url", pdfUrl);
                pdfViewerFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.main_container, pdfViewerFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ImageView testIcon = rootView.findViewById(R.id.test_icon);
        testIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean testApproved2 = sharedPreferences.getBoolean(TEST_APPROVED_KEY5, false);
                int percentage5 = sharedPreferences.getInt("TestPercentage5", 0);

                if (testApproved2 && percentage5 >= 80) {
                    showTestApprovalDialog(percentage5);
                } else {
                    // Iniciar la actividad TestModel1Fragment
                    TestModel5Fragment TestModel5Fragment = new TestModel5Fragment();
                    getParentFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.main_container, TestModel5Fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });



        /*btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean testApproved = sharedPreferences.getBoolean(TEST_APPROVED_KEY5, false);
                int percentage5 = sharedPreferences.getInt("TestPercentage5", 0);

                if (testApproved && percentage5 >= 80) {
                    // Navigate to EtapaDosCurso
                    EtapaCincoCurso etapaDosCurso = new EtapaCincoCurso();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.main_container, etapaDosCurso)
                            .addToBackStack(null)
                            .commit();
                } else {
                    // Show dialog to complete Test 1 first
                    showTestCompletionDialog();
                }
            }
        });*/

        return rootView;
    }

    private void showTestCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Completar Test 5")
                .setMessage("Debes completar el Test 5 (Ataques informáticos sobre el phishing qué deben conocerse) antes de continuar.")
                .setPositiveButton("Entiendo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Optionally, you can navigate to the test fragment here
                    }
                })
                .setCancelable(false)
                .show();
    }


    private void showTestApprovalDialog(int percentage5) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Test Aprobado")
                .setMessage("Ya has aprobado el test con un " + percentage5 + "% de respuestas correctas.")
                .setPositiveButton("Volver a hacer el test", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Reiniciar el test
                        sharedPreferences.edit()
                                .remove(TEST_APPROVED_KEY5)
                                .remove("TestPercentage5")
                                .apply();

                        TestModel1Fragment testModel1Fragment = new TestModel1Fragment();
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                                .replace(R.id.main_container, testModel1Fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }



    private void updateTestStatus(boolean testApproved2) {
        if (testApproved2) {
            int percentage5 = sharedPreferences.getInt("TestPercentage5", 0);
            if (percentage5 >= 80) {
                testStatus.setText("Se aprobó el Test 5 con " + percentage5 + "%");
            } else {
                testStatus.setText("No se aprobó el Test 4 ");
            }
        } else {
            testStatus.setText("Aún no aprobado");
        }
    }

}
