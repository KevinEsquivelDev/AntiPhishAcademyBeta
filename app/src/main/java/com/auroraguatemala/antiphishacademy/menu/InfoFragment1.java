package com.auroraguatemala.antiphishacademy.menu;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment1;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment2;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment3;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment4;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment5;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment6;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment7;
import com.auroraguatemala.antiphishacademy.menu.description_Info.infoFragment8;
import com.auroraguatemala.antiphishacademy.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoFragment1 extends Fragment {

    private String[] titulos = {
            "¿Qué es el Phishing?",
            "¿Cómo funciona el Phishing?",
            "¿Cuáles son los efectos del phishing?",
            "Tipos de Phishing",
            "Vectores del Phishing",
            "¿Cómo reconocer el phishing?",
            "¿Cómo protegerse contra el phishing?",
            "El Phishing en Guatemala"
    };

    private int[] iconos = {
            R.drawable.info_img_1,
            R.drawable.info_img_2,
            R.drawable.info_img_3,
            R.drawable.info_img_4,
            R.drawable.info_img_5,
            R.drawable.info_img_6,
            R.drawable.info_img_7,
            R.drawable.info_img_8
    };

    public InfoFragment1() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.information_fragment, container, false);

        // Mostrar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_information));
        }

        ListView listView = rootView.findViewById(R.id.list);
        ListViewAdapter adapter = new ListViewAdapter(requireContext(), titulos, iconos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Aquí vamos a manejar el click en cada item de la lista
                Fragment newFragment;

                switch (position) {
                    case 0:
                        // Si la posición es 0 (primer item), creamos una instancia de InfoFragment1
                        newFragment = new infoFragment1();
                        break;
                    case 1:
                        // Si la posición es 1 (segundo item), creamos una instancia de InfoFragment2
                        newFragment = new infoFragment2();
                        break;
                    case 2:
                        // Si la posición es 2 (tercer item), creamos una instancia de InfoFragment3
                        newFragment = new infoFragment3();
                        break;
                    case 3:
                        // Si la posición es 3 (cuarto item), creamos una instancia de InfoFragment4
                        newFragment = new infoFragment4();
                        break;
                    case 4:
                        // Si la posición es 4 (quinto item), creamos una instancia de InfoFragment5
                        newFragment = new infoFragment5();
                        break;
                    case 5:
                        // Si la posición es 5 (sexto item), creamos una instancia de InfoFragment6
                        newFragment = new infoFragment6();
                        break;
                    case 6:
                        // Si la posición es 6 (septimo item), creamos una instancia de InfoFragment7
                        newFragment = new infoFragment7();
                        break;
                    case 7:
                        // Si la posición es 7 (octavo item), creamos una instancia de InfoFragment8
                        newFragment = new infoFragment8();
                        break;

                    // Agregar más casos según los fragmentos que quieras mostrar para cada posición
                    default:
                        return;
                }

                // Transacción de fragmentos
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Asigna la animación personalizada al fragmento de entrada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


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

        return rootView;
    }

    private class ListViewAdapter extends ArrayAdapter<String> {
        private String[] titulos;
        private int[] iconos;

        public ListViewAdapter(Context context, String[] titulos, int[] iconos) {
            super(context, R.layout.list_item_info, titulos);
            this.titulos = titulos;
            this.iconos = iconos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View rowView = inflater.inflate(R.layout.list_item_info, parent, false);

            ImageView imageView = rowView.findViewById(R.id.icono);
            TextView textView = rowView.findViewById(R.id.titulo);

            imageView.setImageResource(iconos[position]);
            textView.setText(titulos[position]);

            return rowView;
        }
    }

}
