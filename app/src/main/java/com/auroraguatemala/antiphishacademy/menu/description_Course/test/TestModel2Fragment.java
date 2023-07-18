package com.auroraguatemala.antiphishacademy.menu.description_Course.test;

import static android.content.Context.MODE_PRIVATE;

import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_APPROVED_KEY2;
import static com.auroraguatemala.antiphishacademy.menu.CursoFragment.TEST_PREFERENCE2;

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
import androidx.fragment.app.Fragment;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.CursoFragment;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaDosCurso;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaUnoCurso;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class TestModel2Fragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_model_1, container, false);
        // Bloquear la navegación y el botón de retroceso
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

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

        sharedPreferences = requireContext().getSharedPreferences("TestPreference2", MODE_PRIVATE);
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
        questions.add(new Question("¿Qué es el correo electrónico de suplantación de identidad en el phishing?",
                new String[]{"Un correo electrónico que contiene enlaces maliciosos.", "Un correo electrónico que parece provenir de una fuente confiable pero es falso.", "Un correo electrónico que solicita información confidencial de forma legítima."},
                1));
        questions.add(new Question("¿Cuál es el objetivo principal del correo electrónico de phishing genérico?",
                new String[]{"Solicitar a los destinatarios que realicen transferencias de dinero.", "Engañar a los destinatarios para que crean que están interactuando con una empresa legítima.", "Enviar mensajes masivos a destinatarios al azar sin personalización."},
                1));
        questions.add(new Question("¿En qué se diferencia el spear phishing del correo electrónico de phishing genérico?",
                new String[]{"El spear phishing es más dirigido y personalizado hacia objetivos específicos.", "El correo electrónico de phishing genérico utiliza enlaces maliciosos.", "Ambos tipos son iguales, solo varía el nombre."},
                0));
        questions.add(new Question("¿Qué es el 'phishing ejecutivo' (whaling)?",
                new String[]{"Un tipo de ataque de phishing que se dirige a individuos de alto perfil en una organización.", "Un tipo de ataque de phishing que utiliza nombres de dominio falsos.", "Un tipo de ataque de phishing dirigido a empresas de alto nivel."},
                0));
        questions.add(new Question("¿Cuál es el propósito del correo electrónico de alerta de seguridad en el phishing?",
                new String[]{"Engañar a los destinatarios para que crean que su cuenta está comprometida y revelen información personal.", "Solicitar a los destinatarios que actualicen su software de seguridad.", "Informar a los destinatarios sobre nuevas actualizaciones de seguridad."},
                0));
        questions.add(new Question("¿Cuál de los tipos de engaños de phishing por correo electrónico se basa en el temor y la preocupación de los destinatarios?",
                new String[]{"Correo electrónico de suplantación de identidad (spoofing).", "Correo electrónico de phishing genérico.", "Correo electrónico de alerta de seguridad."},
                2));
        questions.add(new Question("¿Qué es esencial para protegerte contra los engaños de phishing por correo electrónico?",
                new String[]{"Mantener una actitud cautelosa y seguir buenas prácticas de seguridad.", "Proporcionar información personal a través del correo electrónico si se solicita.", "Hacer clic en enlaces sospechosos para verificar su autenticidad."},
                0));
        questions.add(new Question("¿Cuál de los siguientes NO es un signo común de un correo electrónico de phishing?",
                new String[]{"Enlaces maliciosos o adjuntos infectados.", "Tono urgente o alarmante en el mensaje.", "Solicitar la verificación de información confidencial a través de una llamada telefónica."},
                2));
        questions.add(new Question("¿Cuál de los siguientes tipos de engaños de phishing por correo electrónico se dirige a personas de alto perfil en una organización?",
                new String[]{"Correo electrónico de suplantación de identidad (spoofing).", "Spear phishing.", "Correo electrónico de phishing genérico."},
                1));
        questions.add(new Question("¿Cuál es una buena práctica para protegerte contra los engaños de phishing por correo electrónico?",
                new String[]{"Mantenerse actualizado sobre las últimas tácticas y técnicas utilizadas en los ataques de phishing.", "Proporcionar información confidencial a través del correo electrónico si se solicita.", "Hacer clic en enlaces sin verificar su autenticidad."},
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
        int percentage2 = (int) ((currentQuestion / (float) questions.size()) * 100);
        tvPercentage.setText("Test en curso (" + percentage2 + "% completado)");

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

        if (currentQuestion == 0 && cbOption2.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 1 && cbOption3.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 2 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 3 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 4 && cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (currentQuestion == 5 && cbOption3.isChecked()) {
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
        int percentage2 = (int) ((correctAnswers / (float) questions.size()) * 100);
        String resultMessage = "Test completado: " + percentage2 + "% de respuestas correctas";

        editor.putInt("TestPercentage2", percentage2);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Resultado")
                .setMessage(resultMessage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        // Guardar el resultado del test en las preferencias compartidas
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(TEST_PREFERENCE2, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        if (percentage2 >= 80) {
                            editor.putBoolean(TEST_APPROVED_KEY2, true);
                            showApprovalDialog();
                        } else {
                            editor.putBoolean(TEST_APPROVED_KEY2, false);
                            showFailureDialog();
                        }

                        editor.apply();

                        // Enviar el resultado aprobado a CursoFragment
                        if (getActivity() != null) {
                            SharedPreferences cursoPreferences = getActivity().getSharedPreferences(TEST_PREFERENCE2, Context.MODE_PRIVATE);
                            SharedPreferences.Editor cursoEditor = cursoPreferences.edit();
                            cursoEditor.putBoolean(TEST_APPROVED_KEY2, percentage2 >= 80);
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

    // Método para navegar a EtapaUnoCurso
    private void navigateToEtapaUnoCurso() {
        requireActivity().getSupportFragmentManager().popBackStack();
        requireActivity().getSupportFragmentManager().executePendingTransactions();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.main_container, new EtapaDosCurso())
                .commit();


        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

}
