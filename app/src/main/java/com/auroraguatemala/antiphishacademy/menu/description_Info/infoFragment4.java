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

public class infoFragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_4, container, false);

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
        description1.setText("Existen varios tipos de phishing que los ciberdelincuentes utilizan para engañar a las personas y obtener información confidencial. A continuación, se describen algunos de los tipos de phishing más comunes:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("Es el tipo más común de phishing. Los atacantes envían correos electrónicos fraudulentos que se hacen pasar por una entidad legítima, como un banco, una empresa o un servicio en línea. El correo electrónico puede solicitar a la víctima que proporcione información personal o que haga clic en un enlace malicioso que redirige a un sitio web falso.");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("El Smishing es una variante del phishing que se realiza a través de mensajes de texto (SMS) en lugar de correos electrónicos. Los atacantes envían mensajes de texto fraudulentos que pueden contener enlaces maliciosos o solicitar que se responda con información confidencial.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("El Vishing es un tipo de phishing que se realiza a través de llamadas telefónicas. Los atacantes se hacen pasar por representantes de una empresa legítima, como un banco, y solicitan información confidencial, como números de cuenta o contraseñas.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("El Spear phishing es un tipo de ataque más dirigido y personalizado. Los atacantes investigan y recopilan información específica sobre la víctima, como su nombre, puesto de trabajo, dirección de correo electrónico y contactos conocidos. Utilizan esta información para crear correos electrónicos o mensajes falsos que parecen legítimos y creíbles, lo que aumenta las posibilidades de éxito.");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
