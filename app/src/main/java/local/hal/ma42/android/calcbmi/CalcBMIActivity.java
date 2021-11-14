package local.hal.ma42.android.calcbmi50432;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class CalcBMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_bmi);

        findViewById(R.id.bmi).setOnClickListener(ButtonClickListenner);
    }

    View.OnClickListener ButtonClickListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText inputHeight = findViewById(R.id.height);
            String str_height = inputHeight.getText().toString();

            EditText inputWeight = findViewById(R.id.weight);
            String str_weight_kg = inputWeight.getText().toString();

            if (str_height.equals("") || str_weight_kg.equals("")) {
                Toast.makeText(CalcBMIActivity.this, "身長もしくは体重を入力してください。", Toast.LENGTH_LONG).show();
            } else {
                //取得した身長をDouble型に変更する
                Double d_height_cm = Double.valueOf(str_height);

                //取得した体重をDouble型に変更する
                Double d_weight_kg = Double.valueOf(str_weight_kg);


                if(d_weight_kg == 0) {
                    Toast.makeText(CalcBMIActivity.this, "お前は地球外生命体だな！！", Toast.LENGTH_LONG).show();
                } else if (d_height_cm == 0) {
                    Toast.makeText(CalcBMIActivity.this, "お前は地球外生命体だな！！", Toast.LENGTH_LONG).show();
                } else if(d_weight_kg >= 300) {
                    Toast.makeText(CalcBMIActivity.this, "お前は地球外生命体だな！！", Toast.LENGTH_LONG).show();
                } else if (d_height_cm >= 300) {
                    Toast.makeText(CalcBMIActivity.this, "お前は地球外生命体だな！！", Toast.LENGTH_LONG).show();
                } else {
                    Double d_height_m = d_height_cm / 100;

                    BigDecimal bigBmiOutput = new BigDecimal(d_weight_kg / (d_height_m * d_height_m));
                    bigBmiOutput = bigBmiOutput.setScale(1, BigDecimal.ROUND_HALF_UP);
                    int roundBmi = bigBmiOutput.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();


                    BigDecimal bigBmiCheak = new BigDecimal(22 * (d_height_m * d_height_m));
                    bigBmiCheak = bigBmiCheak.setScale(0, BigDecimal.ROUND_HALF_EVEN);

                    if (roundBmi == 22) {
                        TextView hantei = findViewById(R.id.hantei);
                        hantei.setText("BMIは" + bigBmiOutput + "です。\nちょうどいいです。現状を維持しましょう。");
                    } else if (roundBmi  > 22)  {
                        TextView hantei = findViewById(R.id.hantei);
                        hantei.setText("BMIは" + bigBmiOutput + "です\n肥満です。体重" + bigBmiCheak + "kgを目指しましょう。");
                    } else if(roundBmi < 22) {
                        TextView hantei = findViewById(R.id.hantei);
                        hantei.setText("BMIは" + bigBmiOutput + "です。\n痩せすぎです。体重" + bigBmiCheak + "kgを目指しましょう。");
                    }
                }
            }
        }
    };
}
