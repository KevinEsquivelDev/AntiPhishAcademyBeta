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

public class infoFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_2, container, false);

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

        //¿Cómo funciona el Phishing?
        TextView description1 = rootView.findViewById(R.id.description1);
        description1.setText("El phishing es un tipo de ciberataque en el que los delincuentes intentan engañar a las personas para obtener información confidencial, como contraseñas, datos financieros o información personal. A continuación, te explico cómo funciona típicamente el phishing:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("Los atacantes eligen un objetivo o un grupo de posibles víctimas y recopilan información sobre ellos, como direcciones de correo electrónico, nombres de usuarios o incluso detalles de contacto personal. ");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("Los atacantes envían un mensaje o una comunicación fraudulenta a las víctimas, haciéndose pasar por una entidad o una persona legítima. Pueden utilizar correos electrónicos, mensajes de texto, llamadas telefónicas o mensajes en redes sociales para contactar a las víctimas.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("El mensaje fraudulento está diseñado para engañar y manipular a las víctimas. Puede contener elementos convincentes, como el logotipo y la marca de una empresa conocida, enlaces a sitios web falsificados que se asemejan a los legítimos o incluso información personalizada sobre las víctimas para aumentar la credibilidad del mensaje.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("El mensaje fraudulento generalmente contiene una solicitud para que las víctimas proporcionen información confidencial. Puede ser una solicitud de inicio de sesión en una cuenta, detalles de tarjetas de crédito, contraseñas o cualquier otro tipo de información sensible.");

        TextView bulletPoint5 = rootView.findViewById(R.id.bulletPoint5);
        bulletPoint5.setText("Si las víctimas hacen clic en los enlaces proporcionados en el mensaje, pueden ser redirigidas a sitios web falsificados que se ven y se sienten como los sitios legítimos. Estos sitios están diseñados para capturar la información confidencial ingresada por las víctimas.");

        TextView bulletPoint6 = rootView.findViewById(R.id.bulletPoint6);
        bulletPoint6.setText("Una vez que los atacantes obtienen la información confidencial de las víctimas, pueden utilizarla para diversos fines maliciosos, como acceder a cuentas, realizar transacciones fraudulentas o incluso robar la identidad de las víctimas.");


        //TextView bulletPoint6 = rootView.findViewById(R.id.bulletPoint6);
        //bulletPoint6.setText("");
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
