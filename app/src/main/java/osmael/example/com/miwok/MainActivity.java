package osmael.example.com.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private android.widget.TextView numbers;
    private android.widget.TextView family;
    private android.widget.TextView colors;
    private android.widget.TextView phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Criando uma nova intent para abrir o {@link PhrasesActivity}
                Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);

                // Iniciando a nova activity
                startActivity(intent);
            }

        });

        this.colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Criando uma nova intent para abrir o {@link ColorsActivity}
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);

                // Iniciando a nova activity
                startActivity(intent);
            }

        });

        this.family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Criando uma nova intent para abrir o {@link FamilyActivity}
                Intent intent = new Intent(MainActivity.this, FamilyActivity.class);

                // Iniciando a nova activity
                startActivity(intent);
            }

        });

        this.numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Criando uma nova intent para abrir o {@link NumbersActivity}
                Intent intent = new Intent(MainActivity.this, NumbersActivity.class);

                // Iniciando a nova activity
                startActivity(intent);
            }

        });

    }

}