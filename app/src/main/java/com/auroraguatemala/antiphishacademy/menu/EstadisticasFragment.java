package com.auroraguatemala.antiphishacademy.menu;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;

public class EstadisticasFragment extends Fragment {

    private LinearLayout layoutContent1, layoutContent2, layoutContent3, layoutContent4, layoutContent5, layoutContent6, layoutContent7;
    private Button btnExpandCollapse1, btnExpandCollapse2, btnExpandCollapse3, btnExpandCollapse4, btnExpandCollapse5, btnExpandCollapse6, btnExpandCollapse7;

    public EstadisticasFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Estadisticas"
        View rootView = inflater.inflate(R.layout.estadisticas_fragment, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_estadisitica));
        }

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

        // Obtener referencias a los elementos del layout
        layoutContent1 = rootView.findViewById(R.id.layoutContent1);
        layoutContent2 = rootView.findViewById(R.id.layoutContent2);
        layoutContent3 = rootView.findViewById(R.id.layoutContent3);
        layoutContent4 = rootView.findViewById(R.id.layoutContent4);
        layoutContent5 = rootView.findViewById(R.id.layoutContent5);
        layoutContent6 = rootView.findViewById(R.id.layoutContent6);
        layoutContent7 = rootView.findViewById(R.id.layoutContent7);

        btnExpandCollapse1 = rootView.findViewById(R.id.btnExpandCollapse1);
        btnExpandCollapse2 = rootView.findViewById(R.id.btnExpandCollapse2);
        btnExpandCollapse3 = rootView.findViewById(R.id.btnExpandCollapse3);
        btnExpandCollapse4 = rootView.findViewById(R.id.btnExpandCollapse4);
        btnExpandCollapse5 = rootView.findViewById(R.id.btnExpandCollapse5);
        btnExpandCollapse6 = rootView.findViewById(R.id.btnExpandCollapse6);
        btnExpandCollapse7 = rootView.findViewById(R.id.btnExpandCollapse7);

        // Configurar los listeners de los botones de expansión/collapsado
        btnExpandCollapse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent1, btnExpandCollapse1);
            }
        });

        btnExpandCollapse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent2, btnExpandCollapse2);
            }
        });

        btnExpandCollapse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent3, btnExpandCollapse3);
            }
        });

        btnExpandCollapse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent4, btnExpandCollapse4);
            }
        });

        btnExpandCollapse5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent5, btnExpandCollapse5);
            }
        });

        btnExpandCollapse6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent6, btnExpandCollapse6);
            }
        });

        btnExpandCollapse7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleContentVisibility(layoutContent7, btnExpandCollapse7);
            }
        });

        return rootView;
    }

    // Método para alternar la visibilidad del contenido y cambiar el texto del botón
    // Método para alternar la visibilidad del contenido y cambiar el texto del botón
    private void toggleContentVisibility(LinearLayout layoutContent, Button btnExpandCollapse) {
        if (layoutContent.getVisibility() == View.VISIBLE) {
            layoutContent.setVisibility(View.GONE);
            btnExpandCollapse.setText("Ver contenido");
        } else {
            layoutContent.setVisibility(View.VISIBLE);
            btnExpandCollapse.setText("Ocultar contenido");
        }
    }

}
