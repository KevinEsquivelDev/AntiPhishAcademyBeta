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

public class infoFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment_3, container, false);

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
        description1.setText("El phishing puede tener varios efectos negativos tanto para los individuos como para las organizaciones. Estos son algunos de los efectos comunes del phishing:");

        TextView bulletPoint1 = rootView.findViewById(R.id.bulletPoint1);
        bulletPoint1.setText("Uno de los principales efectos del phishing es el robo de información confidencial. Los atacantes pueden obtener acceso a nombres de usuario, contraseñas, números de tarjetas de crédito, información bancaria y otra información personal valiosa. Esta información se utiliza posteriormente para realizar transacciones fraudulentas, robo de identidad u otros delitos financieros.");

        TextView bulletPoint2 = rootView.findViewById(R.id.bulletPoint2);
        bulletPoint2.setText("Los ataques de phishing pueden tener un impacto económico significativo. Las personas y las organizaciones pueden sufrir pérdidas financieras debido a transacciones no autorizadas, robos de cuentas bancarias o fraudes con tarjetas de crédito.");

        TextView bulletPoint3 = rootView.findViewById(R.id.bulletPoint3);
        bulletPoint3.setText("Si una organización es víctima de un ataque de phishing y se roban datos confidenciales de sus clientes, puede resultar en un daño significativo a su reputación. La confianza de los clientes y socios comerciales puede verse afectada, lo que puede llevar a una disminución de las ventas, pérdida de clientes y daño duradero a la imagen de la marca.");

        TextView bulletPoint4 = rootView.findViewById(R.id.bulletPoint4);
        bulletPoint4.setText("Los ciberdelincuentes pueden utilizar la información robada en ataques de suplantación de identidad. Esto implica hacerse pasar por la víctima y llevar a cabo acciones ilegales o fraudulentas en su nombre. Esto puede tener consecuencias legales y financieras graves para la persona cuya identidad ha sido comprometida.");

        TextView bulletPoint5 = rootView.findViewById(R.id.bulletPoint5);
        bulletPoint5.setText("En algunos casos, los ataques de phishing pueden involucrar la descarga o ejecución de malware en el dispositivo de la víctima. Esto puede permitir a los atacantes acceder y controlar el dispositivo, robar información adicional o utilizarlo para llevar a cabo ataques más amplios, como ataques de denegación de servicio (DDoS).");


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

}
