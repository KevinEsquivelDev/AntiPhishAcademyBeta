package com.auroraguatemala.antiphishacademy.menu.description_Info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class infoFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_1, container, false);

        // Código para ocultar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        Button backButton = rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al fragmento InicioFragment con una transición personalizada
                getParentFragmentManager().popBackStack();
                getParentFragmentManager().executePendingTransactions();
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .commit();
                BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });

        // Obtén la referencia al TextView
        TextView description1 = rootView.findViewById(R.id.description1);
        TextView description2 = rootView.findViewById(R.id.description2);

        // Define el texto del TextView
        description1.setText("El phishing es una forma de ciberataque en la que los delincuentes intentan engañar a las personas para obtener información confidencial, como contraseñas, datos financieros o información personal. Los atacantes se hacen pasar por entidades legítimas, como bancos, servicios en línea, empresas o incluso personas conocidas, y utilizan técnicas de ingeniería social para convencer a las víctimas de que revelen información sensible o realicen acciones no deseadas.");
        description2.setText("El phishing generalmente se realiza a través de correos electrónicos, mensajes de texto, llamadas telefónicas o sitios web falsos. Los mensajes o comunicaciones fraudulentas suelen contener enlaces maliciosos que dirigen a las víctimas a sitios web falsificados que se asemejan a los legítimos. Estos sitios pueden solicitar información personal, como nombres de usuario, contraseñas, números de tarjetas de crédito o datos de cuentas bancarias.");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}
