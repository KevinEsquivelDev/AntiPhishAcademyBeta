package com.auroraguatemala.antiphishacademy.menu.description_Course.test;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.auroraguatemala.antiphishacademy.R;
import com.auroraguatemala.antiphishacademy.menu.CursoFragment;
import com.auroraguatemala.antiphishacademy.menu.description_Course.EtapaUnoCurso;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TestModel1Activity extends AppCompatActivity {
    private TextView tvPercentage, tvIntroduction, tvQuestion;
    private CheckBox cbOption1, cbOption2, cbOption3;
    private Button btnNextQuestion;

    // Modelo de datos para las preguntas
    private class Question {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_model_1);

        tvPercentage = findViewById(R.id.title_percentage);
        tvIntroduction = findViewById(R.id.title_introduction);
        tvQuestion = findViewById(R.id.question_title);
        cbOption1 = findViewById(R.id.checkbox_option_1);
        cbOption2 = findViewById(R.id.checkbox_option_2);
        cbOption3 = findViewById(R.id.checkbox_option_3);
        btnNextQuestion = findViewById(R.id.button_next_question);

        sharedPreferences = getSharedPreferences("TestPreference", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Glide.with(this)
                .asGif()
                .load(R.drawable.my_gif_quiz)
                .into((ImageView) findViewById(R.id.gif_image_view));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(getResources().getColor(R.color.primary_color_curso));
        }


        // Agregar las preguntas a la lista
        questions.add(new Question("¿Cuál es el objetivo principal del phishing?",
                new String[]{"Obtener información confidencial de forma fraudulenta.", "Proteger los datos en línea.", "Mejorar la seguridad cibernética."},
                0));
        questions.add(new Question("¿Qué es el spear phishing?",
                new String[]{"Un ataque de phishing dirigido a altos ejecutivos.", "Un ataque de phishing utilizando correos electrónicos genéricos.", "Un ataque de phishing dirigido a individuos al azar."},
                0));
        questions.add(new Question("¿Cuál es un indicio común de un correo electrónico de phishing?",
                new String[]{"Enlaces sospechosos o URLs engañosas.", "Contenido bien redactado sin errores.", "Solicitudes legítimas de información personal."},
                0));
        questions.add(new Question("¿Cuál es una medida recomendada para protegerse del phishing?",
                new String[]{"Mantener el software de seguridad actualizado.", "Hacer clic en enlaces sospechosos para verificar su autenticidad.", "Proporcionar información personal a través de mensajes de correo electrónico."},
                0));
        questions.add(new Question("¿Qué es el Pharming?",
                new String[]{"La técnica de redirigir el tráfico web a sitios falsos.", "La técnica de engañar a las personas para obtener información personal.", "La técnica de enviar correos electrónicos masivos con fines maliciosos."},
                0));
        questions.add(new Question("¿Por qué es importante verificar la autenticidad de los remitentes en los correos electrónicos?",
                new String[]{"Para evitar hacer clic en enlaces sospechosos.", "Para asegurarse de que la solicitud de información sea legítima.", "Para evitar recibir correos electrónicos de phishing."},
                1));
        questions.add(new Question("¿Cuál es una buena práctica para crear contraseñas seguras?",
                new String[]{"Utilizar la misma contraseña para todas las cuentas en línea.", "Utilizar contraseñas cortas y fáciles de recordar.", "Utilizar contraseñas fuertes y únicas para cada cuenta."},
                2));
        questions.add(new Question("¿Qué es el Vishing?",
                new String[]{"Un tipo de ataque de phishing dirigido a altos ejecutivos y personas de alto perfil.", "Es un tipo de phishing que se realiza a través de llamadas telefónicas.", "Un tipo de ataque de phishing que utiliza correos electrónicos masivos."},
                0));
        questions.add(new Question("¿Cuál es un signo común de un mensaje de phishing?",
                new String[]{"Amenazas de consecuencias negativas si no se toma acción inmediata.", "Mensajes redactados de manera impecable.", "Solicitudes legítimas de actualización de información."},
                0));
        questions.add(new Question("¿Qué es recomendable hacer si se sospecha de un ataque de phishing?",
                new String[]{"Reportarlo a la empresa o entidad afectada.", "Proporcionar la información solicitada para evitar consecuencias.", "Ignorarlo y eliminarlo sin hacer nada."},
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
        int percentage = (int) ((currentQuestion / (float) questions.size()) * 100);
        tvPercentage.setText("Test en curso (" + percentage + "% completado)");

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

        if (cbOption1.isChecked()) {
            selectedAnswer = 0;
        } else if (cbOption2.isChecked()) {
            selectedAnswer = 1;
        } else if (cbOption3.isChecked()) {
            selectedAnswer = 2;
        }

        if (selectedAnswer == current.correctAnswer) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        int percentage = (int) ((correctAnswers / (float) questions.size()) * 100);
        String resultMessage = "Test completado: " + percentage + "% de respuestas correctas";

        editor.putInt("TestPercentage", percentage);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado")
                .setMessage(resultMessage)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if (percentage >= 80) {
                            editor.putBoolean("TestApproved", true);
                            editor.apply();
                            showApprovalDialog();
                        } else {
                            editor.putBoolean("TestApproved", false);
                            editor.apply();
                            showFailureDialog();
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para mostrar un diálogo de aprobación
    private void showApprovalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No aprobado")
                .setMessage("No has alcanzado el 80% de respuestas correctas. Vuelve a intentarlo.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // Método para navegar a EtapaUnoCurso
    private void navigateToEtapaUnoCurso() {
        Intent intent = new Intent(this, EtapaUnoCurso.class);
        startActivity(intent);
        finish();

    }

}
