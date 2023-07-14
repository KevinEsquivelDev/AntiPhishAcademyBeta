package com.auroraguatemala.antiphishacademy.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class EditProfileFragment extends Fragment {

    private View rootView;
    private static final int REQUEST_SELECT_IMAGE = 100;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String BACKGROUND_IMAGE_KEY = "backgroundImage";
    private static final String NAME_KEY = "name";
    private static final String AGE_KEY = "age";
    private static final String JOB_KEY = "job";

    private SharedPreferences sharedPreferences;
    private Bitmap backgroundImage;
    private String name;
    private String age;
    private String job;

    private ProgressDialog progressDialog;


    public EditProfileFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento para "EditProfile"
        rootView = inflater.inflate(R.layout.edit_profile, container, false);

        // Inicializar SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);

        // Cargar los datos guardados, si existen
        loadProfileDataFromSharedPreferences();

        // Cargar la imagen de fondo guardada, si existe
        loadImageFromSharedPreferences();

        // Ocultar el BottomNavigationView
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);

        // Obtener una referencia al botón de retroceso
        Button backButton = rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al fragmento ProfileFragment con la transición personalizada
                getParentFragmentManager().popBackStack();
                getParentFragmentManager().executePendingTransactions();
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .commit();
            }
        });

        // Obtener una referencia al botón de edición de imagen de portada
        ImageView editPortrainIcon = rootView.findViewById(R.id.edit_portrain_icon);
        editPortrainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la galería para seleccionar una imagen
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });

        // Obtener una referencia al botón de edición de nombre
        ImageView editNameIcon = rootView.findViewById(R.id.edit_name_icon);
        editNameIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el diálogo de edición de nombre
                showDialogEditName();
            }
        });

        // Obtener una referencia al botón de edición de edad
        ImageView editAgeIcon = rootView.findViewById(R.id.edit_age_icon);
        editAgeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el selector de fecha de nacimiento
                showDatePicker();
            }
        });

        // Obtener una referencia al botón de edición de trabajo
        ImageView editJobIcon = rootView.findViewById(R.id.edit_job_icon);
        editJobIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el diálogo de edición de trabajo
                showDialogEditJob();
            }
        });



        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            // Obtener la URI de la imagen seleccionada
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Verificar la extensión del archivo
                String extension = getMimeType(selectedImageUri);
                if (extension != null && (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png"))) {
                    try {
                        // Decodificar la URI en un objeto Bitmap
                        backgroundImage = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);

                        // Guardar la imagen de fondo en SharedPreferences
                        saveImageToSharedPreferences();

                        // Obtener una referencia al RelativeLayout de id_portada
                        RelativeLayout idPortadaLayout = rootView.findViewById(R.id.id_portada);

                        // Asignar el Bitmap como fondo del RelativeLayout
                        idPortadaLayout.setBackground(new BitmapDrawable(getResources(), backgroundImage));
                    } catch (Exception e) {
                        Log.e("EditProfileFragment", "Error al cargar la imagen desde la galería", e);
                    }
                } else {
                    Toast.makeText(requireContext(), "Por favor, selecciona una imagen en formato JPG, JPEG o PNG.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Obtener la extensión del archivo a partir de la URI
    private String getMimeType(Uri uri) {
        ContentResolver contentResolver = requireActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    // Método para mostrar el diálogo de edición de nombre
    private void showDialogEditName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Editar nombre");
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit, null);
        final EditText editName = dialogView.findViewById(R.id.edit_text_item);
        builder.setView(dialogView);

        // Agregar botón "Guardar" al diálogo
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener el nombre ingresado y actualizar el TextView correspondiente
                String newName = editName.getText().toString();
                TextView nameTextView = rootView.findViewById(R.id.name);
                nameTextView.setText(newName);

                // Guardar el nombre en SharedPreferences
                saveStringToSharedPreferences(NAME_KEY, newName);
            }
        });

        // Agregar botón "Cancelar" al diálogo
        builder.setNegativeButton("Cancelar", null);

        // Mostrar el diálogo
        builder.create().show();
    }

    // Método para mostrar el selector de fecha de nacimiento
    private void showDatePicker() {
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear un DatePickerDialog y establecer la fecha seleccionada
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                // Actualizar el TextView correspondiente con la fecha seleccionada
                TextView ageTextView = rootView.findViewById(R.id.age);
                ageTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + selectedYear);

                // Guardar la edad en SharedPreferences
                saveStringToSharedPreferences(AGE_KEY, dayOfMonth + "/" + (monthOfYear + 1) + "/" + selectedYear);
            }
        }, year, month, day);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
    }

    // Método para mostrar el diálogo de edición de trabajo
    private void showDialogEditJob() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Editar trabajo");
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit, null);
        final EditText editJob = dialogView.findViewById(R.id.edit_text_item);
        builder.setView(dialogView);

        // Agregar botón "Guardar" al diálogo
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener el trabajo ingresado y actualizar el TextView correspondiente
                String newJob = editJob.getText().toString();
                TextView jobTextView = rootView.findViewById(R.id.job);
                jobTextView.setText(newJob);

                // Guardar el trabajo en SharedPreferences
                saveStringToSharedPreferences(JOB_KEY, newJob);
            }
        });

        // Agregar botón "Cancelar" al diálogo
        builder.setNegativeButton("Cancelar", null);

        // Mostrar el diálogo
        builder.create().show();
    }

    // Método para guardar la imagen de fondo en SharedPreferences
    private void saveImageToSharedPreferences() {
        if (backgroundImage != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Convertir el Bitmap en un byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            backgroundImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] imageBytes = baos.toByteArray();

            // Guardar el byte array en SharedPreferences
            editor.putString(BACKGROUND_IMAGE_KEY, Base64.encodeToString(imageBytes, Base64.DEFAULT));
            editor.apply();

            // Actualizar la imagen de fondo en ProfileFragment
            Fragment profileFragment = getParentFragmentManager().findFragmentByTag("ProfileFragment");
            if (profileFragment != null) {
                RelativeLayout idPortadaLayout = profileFragment.getView().findViewById(R.id.id_portada);
                idPortadaLayout.setBackground(new BitmapDrawable(getResources(), backgroundImage));
            }
        }
    }

    // Método para cargar la imagen de fondo desde SharedPreferences
    private void loadImageFromSharedPreferences() {
        String imageBase64 = sharedPreferences.getString(BACKGROUND_IMAGE_KEY, null);
        if (imageBase64 != null) {
            // Convertir el Base64 String a un byte array
            byte[] imageBytes = Base64.decode(imageBase64, Base64.DEFAULT);

            // Decodificar el byte array en un objeto Bitmap con un tamaño reducido
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // Reducir tamaño a 1/4 de la imagen original
            backgroundImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);

            // Obtener una referencia al RelativeLayout de id_portada
            RelativeLayout idPortadaLayout = rootView.findViewById(R.id.id_portada);

            // Asignar el Bitmap como fondo del RelativeLayout
            idPortadaLayout.setBackground(new BitmapDrawable(getResources(), backgroundImage));
        }
    }

    // Método para guardar una cadena en SharedPreferences
    private void saveStringToSharedPreferences(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

        // Actualizar los datos en el fragmento ProfileFragment
        Fragment profileFragment = getParentFragmentManager().findFragmentByTag("ProfileFragment");
        if (profileFragment != null) {
            TextView nameTextView = profileFragment.getView().findViewById(R.id.nombre_text);
            if (key.equals(NAME_KEY)) {
                nameTextView.setText(value);
            }
            // Agregar las demás actualizaciones aquí para age y job
        }
    }

    // Método para cargar los datos del perfil desde SharedPreferences
    private void loadProfileDataFromSharedPreferences() {
        name = sharedPreferences.getString(NAME_KEY, "");
        age = sharedPreferences.getString(AGE_KEY, "");
        job = sharedPreferences.getString(JOB_KEY, "");

        // Actualizar los TextView correspondientes
        TextView nameTextView = rootView.findViewById(R.id.name);
        nameTextView.setText(name);

        TextView ageTextView = rootView.findViewById(R.id.age);
        ageTextView.setText(age);

        TextView jobTextView = rootView.findViewById(R.id.job);
        jobTextView.setText(job);
    }
}


