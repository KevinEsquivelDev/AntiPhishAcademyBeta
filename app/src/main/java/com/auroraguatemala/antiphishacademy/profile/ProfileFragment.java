package com.auroraguatemala.antiphishacademy.profile;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.auroraguatemala.antiphishacademy.MainActivity;
import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "Profile"
        View rootView = inflater.inflate(R.layout.profile, container, false);

        // Código para ocultar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.accent_color));
        }

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

        Button logoutProfileLayout = rootView.findViewById(R.id.logout_button);
        logoutProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ejecutar el logout() de MainActivity
                MainActivity mainActivity = (MainActivity) requireActivity();
                mainActivity.logout();

                // Redirigir a la vista login.xml y ocultar el BottomNavigationView
                Fragment loginFragment = new LoginFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, loginFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        // Obtener una referencia al LinearLayout del perfil
        Button profileLayout = rootView.findViewById(R.id.perfil);
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar al fragmento EditProfileFragment
                Fragment editProfileFragment = new EditProfileFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Aplicar la transición personalizada
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);

                transaction.replace(R.id.main_container, editProfileFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                // Ocultar el BottomNavigationView
                BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        // Obtener los datos del perfil desde SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefsFile", getActivity().MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String job = sharedPreferences.getString("job", "");
        String backgroundImage = sharedPreferences.getString("backgroundImage", "");

        // Actualizar los TextView correspondientes en el layout
        TextView nameTextView = rootView.findViewById(R.id.nombre_text);
        nameTextView.setText(name);

        TextView ageTextView = rootView.findViewById(R.id.edadview);
        ageTextView.setText(age);

        TextView jobTextView = rootView.findViewById(R.id.descripcion_text);
        jobTextView.setText(job);

        // Actualizar el fondo de RelativeLayout
        RelativeLayout idPortadaLayout = rootView.findViewById(R.id.id_portada);
        if (!backgroundImage.isEmpty()) {
            byte[] decodedString = Base64.decode(backgroundImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            idPortadaLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
        }

        return rootView;
    }
}
