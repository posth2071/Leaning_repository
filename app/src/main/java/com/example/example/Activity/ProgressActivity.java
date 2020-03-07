package com.example.example.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.example.R;

/*
 ProgressBar / ProgressDialog 구현 Activity
    ProgressBar - 막대 모양, 진행 정도
        void setProgress(int progress)
        void incrementProgressBy(int diff)
        void setMax(int max)
        void setIndeterminate(boolean bool)
        int  getProgress()


    ProgressDialog - 원 모양, 진행 중
        void setProgressStyle(ProgressDialog.STYLE_SPINNER)
        void setCanceledOnTouchOutside(boolean bool)
        void setMessage(String msg)
        void show()
        void dismiss()
 */
public class ProgressActivity extends Activity {

    private final String TAG = "ProgressActivity";
    private TextView progress_value;
    private ProgressBar progressBar;
    private Button progress_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progress_value = findViewById(R.id.progress_value);

        progress_start = findViewById(R.id.progress_start);
        progress_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Progress_Task task = new Progress_Task();
                task.execute(20);
            }
        });

        progressBar = findViewById(R.id.progress);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(25);

//        progress_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Thread 생성 - Background 작업위해
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // ProgressBar Update - 4번 반복
//                        for (int i = 0; i < 4; i++) {
//                            // runOnUiThread() - Background 작업 중 UI 조작위해
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.incrementProgressBy(20);
//                                    progress_value.setText(String.valueOf(progressBar.getProgress()));
//                                }
//                            });
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.setProgress(20);
//                                progress_value.setText(String.valueOf(progressBar.getProgress()));
//                            }
//                        });
//                    }
//                }).start();     // Thread Background 작업 실행
//            }
//        });
    }

    class Progress_Task extends AsyncTask<Integer, Integer, Void> {
        private ProgressDialog progressDialog = null;       // 원형 ProgressBar 생성
        public Progress_Task() { super(); }

        @Override
        // doInBackground 전에 실행(UI Thread) - 백그라운드 작업 전 초기화 부분
        protected void onPreExecute() {
            super.onPreExecute();
            // ProgressDialog 생성, 레이아웃 변경
            progressDialog = new ProgressDialog(ProgressActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);      // Style - 원 모양 설정
            progressDialog.setMessage("Loadingg...");                           // Message - 표시할 텍스트
            progressDialog.setCanceledOnTouchOutside(false);                    // 터치시 Canceled 막기
            progressDialog.show();                                              // UI 표시
        }

        @Override
        // 백그라운드 작업 시작, UI 조작 불가, onPreExcute() 종료후 바로 호출
        protected Void doInBackground(Integer... ints) {
            for (int i = 0; i < 4; i++) {
                try {
                    // UI Update, publishProgress() - onProgressUpdate 호출
                    publishProgress(ints[0]);
                    Thread.sleep(500);                  // 0.5초 간격 UI Update
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        // UI 조작가능 (UI Thread에서 실행)
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.incrementProgressBy(values[0]);
            progress_value.setText(progressBar.getProgress()+"%");
        }

        @Override
        // UI Thread에서 실행, doInBackground 종료 후 바로 호출
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();       // ProgressDialog 지우기
            progressBar.setProgress(20);
            progress_value.setText(progressBar.getProgress()+"%");
        }
    }
}
