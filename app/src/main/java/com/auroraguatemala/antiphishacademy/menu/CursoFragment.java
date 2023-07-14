package com.auroraguatemala.antiphishacademy.menu;

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
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CursoFragment extends Fragment {

    private ImageView imageView;
    private ImageView iconoTrofeo;
    private TextView tituloProgreso;
    private TextView textoProgreso;
    private ProgressBar barraProgreso;
    private Button btnContinuar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.curso_fragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_curso));
        }

        // Mostrar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        // Inicialización de las vistas
        imageView = rootView.findViewById(R.id.imageView);
        iconoTrofeo = rootView.findViewById(R.id.icono_trofeo);
        tituloProgreso = rootView.findViewById(R.id.titulo_progreso);
        textoProgreso = rootView.findViewById(R.id.texto_progreso);
        barraProgreso = rootView.findViewById(R.id.barra_progreso);
        btnContinuar = rootView.findViewById(R.id.btnContinuar);

        // Configuración inicial de la barra y el texto de progreso
        actualizarProgreso(1);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa lo que quieres que suceda cuando se presiona el botón Continuar
            }
        });

        Button profileIcon = rootView.findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la vista profile.xml con una transición personalizada
                Fragment profileFragment = new ProfileFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asigna la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, profileFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }

    // Método para actualizar la barra de progreso y el texto de progreso
    private void actualizarProgreso(int progreso) {
        barraProgreso.setProgress(progreso);
        textoProgreso.setText(progreso + "/6");
    }
}
