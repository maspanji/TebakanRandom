package ifundip.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int random;
    private int totalBenar;
    private int totalPercobaan;
    private int jawaban;
    private Button buttonTebak;
    private Button buttonSelesai;
    private TextView textViewTebakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = generateRandom();

        buttonTebak = (Button)findViewById(R.id.btnTebak);
        buttonSelesai = (Button) findViewById(R.id.btnSelesai);
        textViewTebakan = (TextView) findViewById(R.id.angkaTebakan);

        buttonTebak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tebakan = textViewTebakan.getText().toString();
                jawaban = Integer.parseInt(tebakan);
                boolean hasilTebakan = cekJawaban();
                Log.d("KUNCI","kuncinya: "+random);
                if(hasilTebakan){
                    tampilHasil("Anda Benar !");
                    totalBenar++;
                    random = generateRandom();
                }
                else{
                    tampilHasil("Anda Salah ! Coba Lagi !!!");
                }
                totalPercobaan++;
            }
        });

        buttonSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double hasil = totalBenar / totalPercobaan * 100;
                tampilHasil("Total Benar : "+ totalBenar+ " dari total percobaan: "+totalPercobaan);
                //reset
                totalBenar = 0;
                totalPercobaan = 0;
                textViewTebakan.setText("");
            }
        });
    }

    private int generateRandom(){
        //membuat random 1 - 10
        return (int)(Math.random()*10);
    }

    private boolean cekJawaban(){
        return jawaban == random;
    }

    private void tampilHasil(String hasil){
        Toast.makeText(MainActivity.this, hasil, Toast.LENGTH_SHORT).show();
    }

}
