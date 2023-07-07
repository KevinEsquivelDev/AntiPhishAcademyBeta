package com.auroraguatemala.antiphishacademy.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auroraguatemala.antiphishacademy.R;

public class EstadisticasFragment extends Fragment {

    public EstadisticasFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Estadisticas"
        View rootView = inflater.inflate(R.layout.estadisticas_fragment, container, false);
        // Configurar el contenido del fragmento "Estadisticas" aquí

        return rootView;
    }
}
