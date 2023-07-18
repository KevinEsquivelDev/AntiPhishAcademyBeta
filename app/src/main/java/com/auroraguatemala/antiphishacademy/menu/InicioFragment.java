package com.auroraguatemala.antiphishacademy.menu;

import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.notification.NotificationFragment;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioFragment extends Fragment {

    public InicioFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Inicio"
        View rootView = inflater.inflate(R.layout.inicio_fragment, container, false);
        // Configurar el contenido del fragmento "Inicio" aquí

        // Mostrar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.accent_color));
        }


        CardView cardInformation = rootView.findViewById(R.id.card_information);
        cardInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la vista information_fragment.xml (InfoFragment) con una transacción de fragmento
                Fragment infoFragment = new InfoFragment1();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asignar la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, infoFragment);
                transaction.commit();

                // Ejecuta la operación de la pila de retroceso en un nuevo Runnable en la cola de mensajes de la vista
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        getParentFragmentManager().beginTransaction().addToBackStack(null).commit();

                        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_info);
                    }
                });
            }
        });


        CardView cardResources = rootView.findViewById(R.id.card_resources);
        cardResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la vista recursos_fragment.xml (RecursosFragment) con una transacción de fragmento
                Fragment recursosFragment = new RecursosFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asignar la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, recursosFragment);
                transaction.commit();

                // Ejecuta la operación de la pila de retroceso en un nuevo Runnable en la cola de mensajes de la vista
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        getParentFragmentManager().beginTransaction().addToBackStack(null).commit();

                        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_recursos);
                    }
                });
            }
        });


        CardView cardCourse = rootView.findViewById(R.id.card_course);
        cardCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la vista curso_fragment.xml (CursoFragment) con una transacción de fragmento
                Fragment cursoFragment = new CursoFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asignar la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, cursoFragment);
                transaction.commit();

                // Ejecuta la operación de la pila de retroceso en un nuevo Runnable en la cola de mensajes de la vista
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        getParentFragmentManager().beginTransaction().addToBackStack(null).commit();

                        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_curso);
                    }
                });
            }
        });


        CardView cardEstadistica = rootView.findViewById(R.id.card_estadistica);
        cardEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la vista estadisticas_fragment.xml (EstadisticasFragment) con una transacción de fragmento
                Fragment estadisticasFragment = new EstadisticasFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asignar la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, estadisticasFragment);
                transaction.commit();

                // Ejecuta la operación de la pila de retroceso en un nuevo Runnable en la cola de mensajes de la vista
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        getParentFragmentManager().beginTransaction().addToBackStack(null).commit();

                        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_estadisticas);
                    }
                });
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

        Button notificationButton = rootView.findViewById(R.id.notification);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar al NotificationFragment con una transición personalizada
                Fragment notificationFragment = new NotificationFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asignar la animación personalizada al fragmento de entrada y salida
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, notificationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return rootView;
    }
}
