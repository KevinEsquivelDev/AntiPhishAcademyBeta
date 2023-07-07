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

public class infoFragment5 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_5, container, false);

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

        TextView description1 = rootView.findViewById(R.id.description1);
        description1.setText("Los vectores del phishing se refieren a los diferentes métodos utilizados por los ciberdelincuentes para llevar a cabo ataques de phishing. A continuación, se presentan algunos de los vectores de phishing más comunes:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("El correo electrónico es uno de los vectores más utilizados en los ataques de phishing. Los atacantes envían mensajes de correo electrónico fraudulentos que parecen provenir de entidades legítimas, como bancos, servicios en línea o empresas reconocidas. Estos correos electrónicos suelen contener enlaces maliciosos que dirigen a los usuarios a sitios web falsos o solicitan información confidencial.");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("Los atacantes también pueden utilizar mensajes de texto (SMS) para llevar a cabo ataques de phishing, en lo que se conoce como Smishing. Envían mensajes de texto fraudulentos que pueden contener enlaces maliciosos o solicitar a los usuarios que respondan con información confidencial.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("El Vishing es un vector de phishing que implica realizar llamadas telefónicas fraudulentas. Los atacantes se hacen pasar por representantes de una organización legítima, como un banco, y solicitan información confidencial, como números de cuenta o contraseñas, bajo el pretexto de una urgencia o problema.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("Los atacantes pueden crear sitios web falsos que se asemejan a los de entidades legítimas, como bancos o servicios en línea populares. Estos sitios web falsos se utilizan para recopilar información confidencial cuando los usuarios intentan iniciar sesión o realizar transacciones.");

        TextView bulletPoint5 = rootView.findViewById(R.id.bulletPoint5);
        bulletPoint5.setText("Los ciberdelincuentes también pueden aprovechar las redes sociales y las plataformas de mensajería instantánea para llevar a cabo ataques de phishing. Pueden enviar mensajes directos fraudulentos o publicaciones engañosas que contienen enlaces maliciosos o solicitudes de información personal.");


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
