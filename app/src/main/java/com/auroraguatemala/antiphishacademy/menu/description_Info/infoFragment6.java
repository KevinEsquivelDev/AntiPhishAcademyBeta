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

public class infoFragment6 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_6, container, false);

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
        description1.setText("Reconocer el phishing puede ser un desafío, ya que los atacantes se esfuerzan por hacer que sus mensajes parezcan legítimos. Sin embargo, hay algunas señales y pautas que pueden ayudarte a identificar posibles intentos de phishing. Aquí hay algunos consejos para reconocer el phishing:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("Antes de hacer clic en un enlace, verifica cuidadosamente la URL. Los atacantes a menudo utilizan direcciones web falsas o ligeramente modificadas que se asemejan a las de sitios legítimos. Presta atención a errores de ortografía, caracteres extraños o cambios sutiles en la URL.");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("Los mensajes de phishing a menudo contienen errores gramaticales, errores ortográficos o frases que no suenan naturales. Las empresas y organizaciones legítimas suelen revisar cuidadosamente sus comunicaciones antes de enviarlas, por lo que los errores de este tipo pueden ser una señal de phishing.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("Desconfía de los mensajes que te soliciten información personal o confidencial, como contraseñas, números de tarjetas de crédito o datos de inicio de sesión. Las entidades legítimas generalmente no solicitarán este tipo de información a través de correos electrónicos no solicitados o mensajes inesperados.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("Los atacantes pueden tratar de generar una sensación de urgencia o miedo para que tomes acciones rápidas y descuidadas. Si un mensaje te presiona para que tomes medidas inmediatas o advierte sobre consecuencias graves si no proporcionas información, es posible que sea un intento de phishing.");

        TextView bulletPoint5 = rootView.findViewById(R.id.bulletPoint5);
        bulletPoint5.setText("Examina cuidadosamente el remitente del mensaje. Si no reconoces la dirección de correo electrónico o si parece sospechosa, es mejor ser cauteloso. Sin embargo, ten en cuenta que los atacantes pueden falsificar direcciones de correo electrónico para que parezcan legítimas, por lo que este no es un método infalible.");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
