package com.auroraguatemala.antiphishacademy.menu;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.description_Resource.descriptionFragment1;
import com.auroraguatemala.antiphishacademy.menu.description_Resource.descriptionFragment2;
import com.auroraguatemala.antiphishacademy.menu.description_Resource.descriptionFragment3;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecursosFragment extends Fragment {

    private ListView listView;
    private ArrayList<Recurso> recursosList;

    public RecursosFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Recursos"
        View rootView = inflater.inflate(R.layout.recursos_fragment, container, false);

        // Mostrar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_recursos));
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


        // Obtener una referencia al ListView en el layout del fragmento
        listView = rootView.findViewById(R.id.listViewRecursos);

        // Crear una lista de objetos Recurso (puedes reemplazar esto con tus propios datos)
        recursosList = new ArrayList<>();
        recursosList.add(new Recurso(R.drawable.resources_img_1, "Sitios Web especializados en Phishing"));
        recursosList.add(new Recurso(R.drawable.resources_img_2, "Blog y artículos sobre el Phishing"));
        recursosList.add(new Recurso(R.drawable.resources_img_3, "Cursos en línea sobre el Phishing"));

        // Crear un adaptador personalizado
        RecursosAdapter adapter = new RecursosAdapter(recursosList);

        // Establecer el adaptador en el ListView
        listView.setAdapter(adapter);

        return rootView;
    }

    private class RecursosAdapter extends ArrayAdapter<Recurso> {

        RecursosAdapter(ArrayList<Recurso> recursosList) {
            super(getActivity(), R.layout.item_recursos, recursosList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                itemView = inflater.inflate(R.layout.item_recursos, parent, false);
            }

            // Obtener el objeto Recurso actual
            Recurso recurso = recursosList.get(position);

            // Actualizar la vista del elemento de la lista
            ImageView icono = itemView.findViewById(R.id.icono);
            TextView titulo = itemView.findViewById(R.id.titulo);
            Button botonIngresar = itemView.findViewById(R.id.boton_ingresar);

            icono.setImageResource(recurso.getIcono());
            titulo.setText(recurso.getTitulo());

            botonIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        // Navegar al fragmento descriptionFragment1
                        Fragment descriptionFragment1 = new descriptionFragment1();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                        // Agregar animaciones personalizadas
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                        transaction.replace(R.id.main_container, descriptionFragment1);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else if (position == 1) {
                        // Navegar al fragmento descriptionFragment2
                        Fragment descriptionFragment2 = new descriptionFragment2();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                        // Agregar animaciones personalizadas
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                        transaction.replace(R.id.main_container, descriptionFragment2);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } else if (position == 2) {
                        // Navegar al fragmento descriptionFragment3
                        Fragment descriptionFragment3 = new descriptionFragment3();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                        // Agregar animaciones personalizadas
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                        transaction.replace(R.id.main_container, descriptionFragment3);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
            });



            return itemView;
        }
    }

    private class Recurso {
        private int icono;
        private String titulo;

        Recurso(int icono, String titulo) {
            this.icono = icono;
            this.titulo = titulo;
        }

        int getIcono() {
            return icono;
        }

        String getTitulo() {
            return titulo;
        }
    }
}
