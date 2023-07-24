package com.auroraguatemala.antiphishacademy.menu.description_Course.test;

import static android.content.Context.MODE_PRIVATE;
import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_APPROVED_KEY4;
import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_PREFERENCE4;

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
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaCuatroCurso;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaTresCurso;
import com.auroraguatemala.antiphishacademy.menu.notification.NotificationFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestModel4Fragment extends Fragment {
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

        sharedPreferences = requireContext().getSharedPreferences("TestPreference4", MODE_PRIVATE);
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
        // Agregar las preguntas a la lista
        questions.add(new Question("¿Qué es el phishing en las redes sociales?",
                new String[]{"a) Un ataque cibernético dirigido a los servidores de redes sociales.",
                        "b) Un método para obtener más seguidores en las redes sociales.",
                        "c) Un engaño en línea en el que los estafadores se hacen pasar por entidades o personas de confianza para obtener información personal o financiera."},
                2));
        questions.add(new Question("¿Qué hacen los estafadores en el phishing en las redes sociales?",
                new String[]{"a) Crear perfiles falsos y enviar mensajes maliciosos a otros usuarios.",
                        "b) Publicar anuncios falsos en las redes sociales.",
                        "c) Enviar mensajes a los usuarios pidiendo donaciones para organizaciones benéficas falsas."},
                0));
        questions.add(new Question("¿Qué tipo de información intentan obtener los estafadores en el phishing en las redes sociales?",
                new String[]{"a) Nombres de usuario y contraseñas.",
                        "b) Información de tarjetas de crédito y números de seguridad social.",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Cuál es una táctica común utilizada en el phishing en las redes sociales?",
                new String[]{"a) Crear perfiles falsos que parezcan auténticos.",
                        "b) Enviar mensajes amistosos a todos los contactos de una persona.",
                        "c) Solicitar amistad a personas famosas o influyentes."},
                0));
        questions.add(new Question("¿Cómo pueden los estafadores obtener información personal a través del phishing en las redes sociales?",
                new String[]{"a) Mediante el envío de enlaces maliciosos que redirigen a páginas falsas de inicio de sesión.",
                        "b) A través de mensajes directos que solicitan información personal.",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Qué precaución se puede tomar para protegerse contra el phishing en las redes sociales?",
                new String[]{"a) Verificar la autenticidad de los perfiles antes de aceptar solicitudes de amistad o conexiones.",
                        "b) Evitar hacer clic en enlaces sospechosos o descargar archivos adjuntos no verificados.",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Qué se debe hacer si se sospecha que se ha sido víctima de phishing en las redes sociales?",
                new String[]{"a) Cambiar inmediatamente la contraseña de la cuenta afectada.",
                        "b) Informar a las autoridades policiales.",
                        "c) Notificar a la plataforma de redes sociales y tomar medidas para proteger otras cuentas relacionadas."},
                2));
        questions.add(new Question("¿Por qué es importante ajustar la configuración de privacidad en las redes sociales?",
                new String[]{"a) Para controlar quién puede ver tu información personal y qué publicaciones se muestran públicamente.",
                        "b) Para evitar que los estafadores accedan a tu perfil.",
                        "c) Para ocultar todas las publicaciones antiguas en tu perfil."},
                0));
        questions.add(new Question("¿Cuál es una señal de advertencia de un posible ataque de phishing en las redes sociales?",
                new String[]{"a) Un perfil con poca información o fotos poco realistas.",
                        "b) Mensajes o publicaciones que solicitan información personal o financiera.",
                        "c) Ambas respuestas anteriores son correctas."},
                2));
        questions.add(new Question("¿Qué papel juega la educación y la conciencia en la protección contra el phishing en las redes sociales?",
                new String[]{"a) Son fundamentales para reconocer las señales de advertencia y tomar precauciones.",
                        "b) No tienen ningún impacto en la protección contra el phishing en las redes sociales.",
                        "c) Solo son importantes para las empresas de redes sociales, no para los usuarios individuales."},
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
        int percentage4 = (int) ((currentQuestion / (float) questions.size()) * 100);
        tvPercentage.setText("Test en curso (" + percentage4 + "% completado)");

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
        } else if (currentQuestion == 1 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 2 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 3 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 4 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 5 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 6 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 7 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 8 && cbOption3.isChecked()) {
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
        int percentage4 = (int) ((correctAnswers / (float) questions.size()) * 100);
        String resultMessage = "Test completado: " + percentage4 + "% de respuestas correctas";

        editor.putInt("TestPercentage4", percentage4);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Resultado")
                .setMessage(resultMessage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        // Guardar el resultado del test en las preferencias compartidas
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(TEST_PREFERENCE4, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        if (percentage4 >= 80) {
                            editor.putBoolean(TEST_APPROVED_KEY4, true);
                            showApprovalDialog();
                        } else {
                            editor.putBoolean(TEST_APPROVED_KEY4, false);
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
                        notificationManager.notify(/*ID_DE_LA_NOTIFICACION*/ 4, builder.build());

                        //Guardar la notificación
                        SharedPreferences sharedPreferencesN4 = requireContext().getSharedPreferences("NotificationPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor notificationEditor = sharedPreferencesN4.edit();

                        // Obtiene el Set actual de notificaciones, si existe, o crea uno nuevo si no.
                        Set<String> notifications = sharedPreferencesN4.getStringSet("notificationTitle", new HashSet<String>());

                        // Añade la nueva notificación al Set.
                        notifications.add("¡Felicidades!\nHaz completado el módulo 4 del curso\n \"Las redes sociales y el phishing\"");

                        // Almacena el Set actualizado de notificaciones.
                        notificationEditor.putStringSet("notificationTitle", notifications);
                        notificationEditor.apply();

                        // Enviar el resultado aprobado a CursoFragment
                        if (getActivity() != null) {
                            SharedPreferences cursoPreferences = getActivity().getSharedPreferences(TEST_PREFERENCE4, Context.MODE_PRIVATE);
                            SharedPreferences.Editor cursoEditor = cursoPreferences.edit();
                            cursoEditor.putBoolean(TEST_APPROVED_KEY4, percentage4 >= 80);
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
                .replace(R.id.main_container, new EtapaCuatroCurso())
                .commit();


        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

}
