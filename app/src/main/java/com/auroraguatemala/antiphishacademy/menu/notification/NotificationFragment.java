package com.auroraguatemala.antiphishacademy.menu.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.ListView;

import com.auroraguatemala.antiphishacademy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationFragment extends Fragment {

    private ListView listView;
    private List<Notification> notificationList;
    private NotificationAdapter notificationAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notification_fragment, container, false);

        // Mostrar el BottomNavigationView
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

        // Inicializa la lista de notificaciones y el adaptador
        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(requireContext(), R.layout.item_notification, notificationList);

        // Configura el ListView
        listView = rootView.findViewById(R.id.NlistViewRecursos);
        listView.setAdapter(notificationAdapter);

        //Obtener las notificaciones
        SharedPreferences sharedPreferencesN = requireContext().getSharedPreferences("NotificationPref", Context.MODE_PRIVATE);
        Set<String> notificationSet = sharedPreferencesN.getStringSet("notificationTitle", new HashSet<String>());

        // Añade las notificaciones a la lista y notifica al adaptador sobre los cambios de datos
        for (String notificationTitle : notificationSet) {
            notificationList.add(new Notification(notificationTitle));
        }
        notificationAdapter.notifyDataSetChanged();

        return rootView;
    }
}
