package com.example.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.example.R;


/*
    시크바(SeekBarActivity) - ProgressActivityBar 확장 + onSeekBarActivityChangedListenr 이벤트 처리
 */
public class SeekBarActivity extends Activity implements View.OnClickListener {
    private final String TAG = "SeekBarActivity";

    private TextView seekbar_value;
    private EditText seekbar_input;
    private SeekBar seekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        seekbar_value = findViewById(R.id.seekbar_value);                   // SeekBar Progress 값 표시, TextView
        seekbar_input = findViewById(R.id.seekbar_input);                   // 변경할 Progress 입력, EditText

        Button seekbar_progress = findViewById(R.id.seekbar_progress);      // Progress 입력값 설정, Button
        seekbar_progress.setOnClickListener(this);
        Button seekbar_increment = findViewById(R.id.seekbar_increment);    // Increment 현재값 기준 더하기/빼기, Button
        seekbar_increment.setOnClickListener(this);

        seekBar = findViewById(R.id.seekbar);                               // SeekBar View
        // OnSeekBarChange 리스너 - Seekbar 값 변경시 이벤트처리 Listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // onProgressChange - Seekbar 값 변경될때마다 호출
                Log.d(TAG, String.format("onProgressChanged 값 변경 중 : progress [%d] fromUser [%b]", progress, fromUser));
                seekbar_value.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // onStartTeackingTouch - SeekBar 값 변경위해 첫 눌림에 호출
                Log.d(TAG, String.format("onStartTrackingTouch 값 변경 시작 : progress [%d]", seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // onStopTrackingTouch - SeekBar 값 변경 끝나고 드래그 떼면 호출
                Log.d(TAG, String.format("onStopTrackingTouch 값 변경 종료: progress [%d]", seekBar.getProgress()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        int value = Integer.parseInt(seekbar_input.getText().toString());
        seekbar_input.setText("");
        seekbar_input.clearFocus();

        switch (view.getId()) {
            case R.id.seekbar_progress:
                // 입력 progress 값이 양수이면서 && 최대범위 보다 작은지 검사
                if (value >= 0 && value <= seekBar.getMax()){
                    seekBar.setProgress(value);
                } else {
                    Toast.makeText(this, "입력 범위 오류", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.seekbar_increment:
                    seekBar.incrementProgressBy(value);
                break;
        }
    }
}

