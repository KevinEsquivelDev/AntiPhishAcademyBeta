package com.auroraguatemala.antiphishacademy.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auroraguatemala.antiphishacademy.R;


public class CursoFragment extends Fragment {

    public CursoFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Curso"
        View rootView = inflater.inflate(R.layout.curso_fragment, container, false);
        // Configurar el contenido del fragmento "Curso" aquí

        return rootView;
    }
}
