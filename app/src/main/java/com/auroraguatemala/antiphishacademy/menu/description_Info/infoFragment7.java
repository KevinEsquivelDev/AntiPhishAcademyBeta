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

public class infoFragment7 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_7, container, false);

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
        description1.setText("Protegerse contra el phishing es fundamental para salvaguardar tus datos personales y evitar ser víctima de fraudes o robos de identidad. Aquí hay algunas medidas importantes que puedes tomar para protegerte contra el phishing:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("Aprende sobre el phishing, sus técnicas y las señales de advertencia. Mantente informado sobre los nuevos métodos utilizados por los ciberdelincuentes para engañar a las personas. Comparte esta información con tus familiares y colegas para que también estén preparados.");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("No abras ni respondas a correos electrónicos, mensajes de texto o mensajes en redes sociales que parezcan sospechosos. Evita hacer clic en enlaces o descargar archivos adjuntos de mensajes no solicitados o de remitentes desconocidos.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("Examina cuidadosamente las direcciones de correo electrónico de los remitentes y verifica si coinciden con las direcciones legítimas de las organizaciones. Ten en cuenta que los atacantes pueden falsificar direcciones, así que presta atención a cualquier señal de sospecha.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("No compartas información personal, contraseñas, números de tarjetas de crédito u otra información confidencial a través de correos electrónicos, mensajes o sitios web sospechosos. Las organizaciones legítimas rara vez solicitarán este tipo de información a través de métodos no seguros.");

        TextView bulletPoint5 = rootView.findViewById(R.id.bulletPoint5);
        bulletPoint5.setText("Habilita la autenticación de dos factores siempre que sea posible, especialmente para cuentas y servicios importantes. Esto proporciona una capa adicional de seguridad al requerir un segundo paso de verificación, como un código enviado a tu teléfono móvil, además de tu contraseña.");

        TextView bulletPoint6 = rootView.findViewById(R.id.bulletPoint6);
        bulletPoint6.setText("Asegúrate de instalar las actualizaciones de seguridad y los parches de software más recientes en tus dispositivos. Las actualizaciones suelen incluir correcciones de seguridad importantes que pueden protegerte contra vulnerabilidades utilizadas en ataques de phishing.");

        TextView bulletPoint7 = rootView.findViewById(R.id.bulletPoint7);
        bulletPoint7.setText("Instala y actualiza regularmente software antivirus y soluciones de seguridad confiables en tus dispositivos. Estas herramientas pueden ayudar a detectar y bloquear sitios web, correos electrónicos o archivos maliciosos relacionados con el phishing.");

        TextView bulletPoint8 = rootView.findViewById(R.id.bulletPoint8);
        bulletPoint8.setText("Antes de ingresar información confidencial en un sitio web, verifica que el sitio sea seguro y legítimo. Busca el candado en la barra de direcciones y asegúrate de que la URL comience con \"https://\", lo que indica una conexión segura.");

        TextView bulletPoint9 = rootView.findViewById(R.id.bulletPoint9);
        bulletPoint9.setText("Siempre mantén una actitud de precaución y desconfía de cualquier mensaje o solicitud inesperada que parezca sospechosa. No te dejes presionar por urgencias o amenazas. Tómate tu tiempo para investigar y verificar la autenticidad de la solicitud antes de tomar cualquier acción.");
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
