package com.auroraguatemala.antiphishacademy.menu.description_Resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class descriptionFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar la vista resource_fragment_1.xml
        View rootView = inflater.inflate(R.layout.resource_fragment_2, container, false);
        // Código para ocultar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        Button backButton = rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Volver al fragmento anterior en la pila de retroceso
                    getParentFragmentManager().popBackStack();
                    getParentFragmentManager().executePendingTransactions();
                    getParentFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                            .commit();
                    BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } catch (IllegalStateException e) {
                    // Manejar la excepción IllegalStateException aquí
                    // Realiza las acciones necesarias para volver al fragmento anterior o realizar otra lógica requerida
                }
            }
        });

        // Obtén la referencia al TextView
        TextView description1 = rootView.findViewById(R.id.description1);
        description1.setText("Aquí tienes algunos blogs y sitios web especializados en publicar artículos informativos y actualizados sobre phishing:");

        TextView disclaimerTextView = rootView.findViewById(R.id.disclaimerTextView);
        disclaimerTextView.setText("Estos blogs y sitios web suelen publicar regularmente artículos informativos, análisis de casos, consejos prácticos y noticias relacionadas con el phishing y la ciberseguridad en general. Te recomendaría visitarlos regularmente para mantenerte actualizado sobre las últimas amenazas y tendencias en el mundo del phishing.");

        return rootView;
    }
}
