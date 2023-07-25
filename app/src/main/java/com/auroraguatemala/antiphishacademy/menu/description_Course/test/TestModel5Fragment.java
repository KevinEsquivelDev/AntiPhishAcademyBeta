package com.auroraguatemala.antiphishacademy.menu.description_Course.test;

import static android.content.Context.MODE_PRIVATE;
import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_APPROVED_KEY5;
import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_PREFERENCE5;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaCincoCurso;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaCuatroCurso;
import com.auroraguatemala.antiphishacademy.menu.notification.NotificationFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestModel5Fragment extends Fragment {
    private TextView tvPercentage, tvIntroduction, tvQuestion;
    private CheckBox cbOption1, cbOption2, cbOption3;
    private Button btnNextQuestion;

    // Modelo de datos para las preguntas
    private static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    // La lista de preguntas
    private List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0; // mantener el índice de la pregunta actual
    private int correctAnswers = 0; // contador de respuestas correctas

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String CHANNEL_ID = "mi_canal_de_notificacion";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_model_1, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Mostrar un diálogo para indicar que el test debe ser completado
                showTestCompletionDialog();
            }
        });


        tvPercentage = view.findViewById(R.id.title_percentage);
        tvIntroduction = view.findViewById(R.id.title_introduction);
        tvQuestion = view.findViewById(R.id.question_title);
        cbOption1 = view.findViewById(R.id.checkbox_option_1);
        cbOption2 = view.findViewById(R.id.checkbox_option_2);
        cbOption3 = view.findViewById(R.id.checkbox_option_3);
        btnNextQuestion = view.findViewById(R.id.button_next_question);

        sharedPreferences = requireContext().getSharedPreferences("TestPreference5", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Glide.with(this)
                .asGif()
                .load(R.drawable.my_gif_quiz)
                .into((ImageView) view.findViewById(R.id.gif_image_view));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            requireActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_curso));
        }

        // Agregar las preguntas a la lista
        questions.add(new Question("¿Qué es una tienda en línea falsa?",
                new String[]{"a) Una tienda que vende productos de baja calidad.",
                        "b) Una tienda que ofrece descuentos exclusivos.",
                        "c) Una tienda creada por estafadores para engañar a los compradores y obtener su información personal y financiera."},
                2));
        questions.add(new Question("¿Qué se debe verificar al realizar compras en línea para asegurarse de que una tienda sea auténtica?",
                new String[]{"a) La dirección física y el número de teléfono de la tienda.",
                        "b) El candado en la barra de direcciones y la URL que comienza con \"https://\".",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Por qué es importante leer las políticas de privacidad y devolución de una tienda en línea antes de realizar una compra?",
                new String[]{"a) Para verificar si la tienda tiene una buena reputación.",
                        "b) Para asegurarse de que la tienda utilizará adecuadamente la información personal del comprador.",
                        "c) Ambas respuestas anteriores son correctas."},
                1));
        questions.add(new Question("¿Cuál es un método de pago seguro para utilizar al realizar compras en línea?",
                new String[]{"a) Transferencia bancaria directa.",
                        "b) Tarjetas de crédito o servicios de pago confiables.",
                        "c) Pago en efectivo al recibir el producto."},
                1));
        questions.add(new Question("¿Por qué es importante investigar las opiniones de otros usuarios sobre una tienda en línea antes de realizar una compra?",
                new String[]{"a) Para asegurarse de obtener descuentos adicionales.",
                        "b) Para verificar la calidad de los productos ofrecidos por la tienda.",
                        "c) Para conocer las experiencias de otros compradores y evaluar la confiabilidad de la tienda."},
                2));
        questions.add(new Question("¿Qué es el phishing de actualización de datos bancarios?",
                new String[]{"a) Una práctica legítima utilizada por los bancos para mantener la información de los clientes actualizada.",
                        "b) Un tipo de estafa en línea en el que los estafadores se hacen pasar por entidades bancarias legítimas para obtener información personal y financiera de los usuarios.",
                        "c) Un proceso automático en el que los bancos actualizan automáticamente los datos de los clientes sin su conocimiento."},
                1));
        questions.add(new Question("¿Qué hacen los estafadores en el phishing de actualización de datos bancarios?",
                new String[]{"a) Envían correos electrónicos o mensajes falsos solicitando a los usuarios que actualicen su información personal y financiera.",
                        "b) Crean sitios web falsos que imitan la apariencia de los sitios web de entidades bancarias legítimas.",
                        "c) Utilizan técnicas de ingeniería social para crear un sentido de urgencia y persuadir a los usuarios a proporcionar su información."},
                0));
        questions.add(new Question("¿Cómo puedes verificar la autenticidad de un correo electrónico o mensaje de actualización de datos bancarios?",
                new String[]{"a) Prestar atención a los errores gramaticales u ortográficos.",
                        "b) Verificar la dirección de correo electrónico del remitente.",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Qué precaución debes tomar al acceder al sitio web de tu banco para realizar una actualización de datos?",
                new String[]{"a) Hacer clic en el enlace proporcionado en el correo electrónico o mensaje recibido.",
                        "b) Escribir directamente la URL del sitio web en el navegador o utilizar un marcador guardado previamente.",
                        "c) Descargar una aplicación de terceros para acceder al sitio web del banco."},
                1));
        questions.add(new Question("¿Qué información personal o financiera nunca debes revelar en respuesta a un correo electrónico o mensaje de actualización de datos?",
                new String[]{"a) Números de tarjetas de crédito y contraseñas.",
                        "b) Dirección de correo electrónico y número de teléfono.",
                        "c) Nombre y apellido."},
                0));

        showQuestion(questions.get(currentQuestion));

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cbOption1.isChecked() && !cbOption2.isChecked() && !cbOption3.isChecked()) {
                    showSelectionDialog();
                    return;
                }

                checkAnswer();
                if (currentQuestion < questions.size() - 1) {
                    currentQuestion++; // incrementa el índice de la pregunta
                    showQuestion(questions.get(currentQuestion)); // muestra la siguiente pregunta
                } else {
                    showResult();
                }
            }
        });

        return view;
    }

    private void showTestCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Completar el Test")
                .setMessage("Debes completar el test antes de continuar.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar una pregunta y sus opciones de respuesta
    private void showQuestion(Question question) {
        tvQuestion.setText(question.question);
        cbOption1.setText(question.options[0]);
        cbOption2.setText(question.options[1]);
        cbOption3.setText(question.options[2]);

        // Restablece las casillas de verificación para cada nueva pregunta
        cbOption1.setChecked(false);
        cbOption2.setChecked(false);
        cbOption3.setChecked(false);

        // Actualizar el porcentaje de progreso
        int percentage5 = (int) ((currentQuestion / (float) questions.size()) * 100);
        tvPercentage.setText("Test en curso (" + percentage5 + "% completado)");

        // Habilitar o deshabilitar el botón de siguiente pregunta según las selecciones del usuario
        btnNextQuestion.setEnabled(false);
        cbOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbOption2.setChecked(false);
                cbOption3.setChecked(false);
                btnNextQuestion.setEnabled(true);
            }
        });
        cbOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbOption1.setChecked(false);
                cbOption3.setChecked(false);
                btnNextQuestion.setEnabled(true);
            }
        });
        cbOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbOption1.setChecked(false);
                cbOption2.setChecked(false);
                btnNextQuestion.setEnabled(true);
            }
        });
    }

    // Método para verificar la respuesta seleccionada
    private void checkAnswer() {
        Question current = questions.get(currentQuestion);
        int selectedAnswer = -1;

        if (currentQuestion == 0 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 1 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 2 && cbOption2.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 3 && cbOption2.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 4 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 5 && cbOption2.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 6 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 7 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 8 && cbOption2.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 9 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        }

        if (selectedAnswer == 0) {
            // Respuesta correcta
            correctAnswers++;
            showCorrectDialog();
        } else {
            // Respuesta incorrecta
            showIncorrectDialog(current.correctAnswer);
        }
    }


    // Método para mostrar un diálogo de selección de respuesta
    private void showSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Respuesta no seleccionada")
                .setMessage("Debes seleccionar al menos una respuesta.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar un diálogo de respuesta correcta
    private void showCorrectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("¡Respuesta correcta!")
                .setMessage("¡Has seleccionado la respuesta correcta!")
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar un diálogo de respuesta incorrecta
    private void showIncorrectDialog(int correctAnswer) {
        String correctOption = questions.get(currentQuestion).options[correctAnswer];

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Respuesta incorrecta")
                .setMessage("La respuesta correcta es: " + correctOption)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar el resultado final
    private void showResult() {
        // Calcular el porcentaje final
        int percentage5 = (int) ((correctAnswers / (float) questions.size()) * 100);
        String resultMessage = "Test completado: " + percentage5 + "% de respuestas correctas";

        editor.putInt("TestPercentage5", percentage5);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Resultado")
                .setMessage(resultMessage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        // Guardar el resultado del test en las preferencias compartidas
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(TEST_PREFERENCE5, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        if (percentage5 >= 80) {
                            editor.putBoolean(TEST_APPROVED_KEY5, true);
                            showApprovalDialog();
                        } else {
                            editor.putBoolean(TEST_APPROVED_KEY5, false);
                            showFailureDialog();
                        }

                        editor.apply();

                        // Crear una notificación
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                                .setSmallIcon(R.drawable.notification) // Icono de la notificación (asegúrate de tener el recurso drawable "ic_notification")
                                .setContentTitle("¡Nuevo logro!")
                                .setContentText("Haz obtenido un nuevo logro")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);

                        // Intent para abrir el fragmento NotificationFragment
                        Intent notificationIntent = new Intent(requireContext(), NotificationFragment.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);

                        // Mostrar la notificación
                        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(/*ID_DE_LA_NOTIFICACION*/ 5, builder.build());

                        //Guardar la notificación
                        SharedPreferences sharedPreferencesN5 = requireContext().getSharedPreferences("NotificationPref5", Context.MODE_PRIVATE);
                        SharedPreferences.Editor notificationEditor = sharedPreferencesN5.edit();

                        // Obtiene el Set actual de notificaciones, si existe, o crea uno nuevo si no.
                        Set<String> notifications = sharedPreferencesN5.getStringSet("notificationTitle", new HashSet<String>());

                        // Añade la nueva notificación al Set.
                        notifications.add("¡Felicidades!\nHaz completado el módulo 5 del curso\n \"Ataques informáticos sobre el phishing qué deben conocerse\"");

                        // Almacena el Set actualizado de notificaciones.
                        notificationEditor.putStringSet("notificationTitle", notifications);
                        notificationEditor.apply();

                        // Enviar el resultado aprobado a CursoFragment
                        if (getActivity() != null) {
                            SharedPreferences cursoPreferences = getActivity().getSharedPreferences(TEST_PREFERENCE5, Context.MODE_PRIVATE);
                            SharedPreferences.Editor cursoEditor = cursoPreferences.edit();
                            cursoEditor.putBoolean(TEST_APPROVED_KEY5, percentage5 >= 80);
                            cursoEditor.apply();
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }



    // Método para mostrar un diálogo de aprobación
    private void showApprovalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("¡Aprobado!")
                .setMessage("¡Has aprobado el test!")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        navigateToEtapaUnoCurso();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar un diálogo de no aprobación
    private void showFailureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("No aprobado")
                .setMessage("No has alcanzado el 80% de respuestas correctas. Vuelve a intentarlo.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        navigateToEtapaUnoCurso();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para navegar a EtapaTresCurso
    private void navigateToEtapaUnoCurso() {
        requireActivity().getSupportFragmentManager().popBackStack();
        requireActivity().getSupportFragmentManager().executePendingTransactions();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.main_container, new EtapaCincoCurso())
                .commit();


        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

}
