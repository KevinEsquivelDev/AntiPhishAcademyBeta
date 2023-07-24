package com.auroraguatemala.antiphishacademy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.auroraguatemala.antiphishacademy.login.LoginFragment;
import com.auroraguatemala.antiphishacademy.menu.CursoFragment;
import com.auroraguatemala.antiphishacademy.menu.EstadisticasFragment;
import com.auroraguatemala.antiphishacademy.menu.InfoFragment1;
import com.auroraguatemala.antiphishacademy.menu.InicioFragment;
import com.auroraguatemala.antiphishacademy.menu.RecursosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean isLoggedIn = false;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.auroraguatemala.antiphishacademy", MODE_PRIVATE);
        isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);


        if (savedInstanceState == null) {
            if (isLoggedIn) {
                showFragment(new InicioFragment());
                bottomNavigationView.setVisibility(View.VISIBLE);
                setupBottomNavigation();
            } else {
                showFragment(new LoginFragment());
                showNotificationPermissionDialog();
                bottomNavigationView.setVisibility(View.GONE);
            }
        }


    }

    private void showNotificationPermissionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Permisos de Notificaciones")
                .setMessage("Para recibir notificaciones, necesitamos que nos des permisos de notificaciones. Haz clic en 'Ir a ajustes' para otorgar los permisos.")
                .setPositiveButton("Ir a ajustes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openNotificationSettings();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void openNotificationSettings() {
        // Abrir la configuración de notificaciones para la aplicación
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(intent);

        // Marcar que ya se mostró el diálogo
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstTime", false);
        editor.apply();
    }


    public void loginSuccessful() {
        isLoggedIn = true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
        showFragment(new InicioFragment());
        bottomNavigationView.setVisibility(View.VISIBLE);
        setupBottomNavigation();
    }

    public void logout() {
        isLoggedIn = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
        showFragment(new LoginFragment());
        bottomNavigationView.setVisibility(View.GONE);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment;
                int itemColorId;
                switch (getResources().getResourceName(item.getItemId())) {
                    case "com.auroraguatemala.antiphishacademy:id/navigation_inicio":
                        selectedFragment = new InicioFragment();
                        itemColorId = R.color.selector_color_inicio;
                        break;
                    case "com.auroraguatemala.antiphishacademy:id/navigation_info":
                        selectedFragment = new InfoFragment1();
                        itemColorId = R.color.selector_color_info;
                        break;
                    case "com.auroraguatemala.antiphishacademy:id/navigation_recursos":
                        selectedFragment = new RecursosFragment();
                        itemColorId = R.color.selector_color_recursos;
                        break;
                    case "com.auroraguatemala.antiphishacademy:id/navigation_curso":
                        selectedFragment = new CursoFragment();
                        itemColorId = R.color.selector_color_curso;
                        break;
                    case "com.auroraguatemala.antiphishacademy:id/navigation_estadisticas":
                        selectedFragment = new EstadisticasFragment();
                        itemColorId = R.color.selector_color_estadisticas;
                        break;
                    default:
                        return false;
                }

                bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(getApplicationContext(), itemColorId));
                bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(getApplicationContext(), itemColorId));

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                        selectedFragment).commit();
                return true;
            }
        });
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }



}
