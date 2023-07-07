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

public class infoFragment8 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_8, container, false);

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
        description1.setText("A pesar de la popularidad del malware en la industria de ciberseguridad, la mayor amenaza en América Latina es el phishing, es decir, los mensajes fraudulentos enviados por correo electrónico, SMS y, sobre todo, a través de redes sociales y apps de mensajería como WhatsApp. Una de las amenazas más comunes en el ciberespacio es el phishing. En los últimos años, en América Latina, se reportaron 38 millones de ataques, que figuran a 110 ataques por minuto. Del 2020 al 2022 creció el número de casos de phishing debido a que el tiempo que pasamos frente a las pantallas aumentó debido a la pandemia. En Guatemala hubo un incremento del 188 por ciento hasta agosto de este año. Es el país de Latinoamérica que tuvo más ciberamenazas de este tipo. Por último, el estudio revela los principales intereses de los ataques de phishing en Guatemala y America latina son: el 27% busca robar credenciales de banca por Internet/móvil, 22% pretende robar credenciales de redes sociales, 18% roba credenciales de servicios en línea (tiendas online, streaming, etc.), el 9% utiliza temas de servicios financieros para robar contraseñas y el 7% quiere datos de pago (tarjeta de crédito).");

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
