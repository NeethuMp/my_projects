package com.example.neethu.asynctaskactivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    Button click;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        click = (Button) findViewById(R.id.button);
        click.setOnClickListener(this);
    }

    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
            click.setEnabled(true);
        }
        super.onDestroy();
    }


    public void onClick(View v) {
        v.setEnabled(false);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>()

        {
            protected void onPreExecute() {
                dialog = new ProgressDialog(context);
                dialog.setTitle("Processing...");
                dialog.setMessage("Please wait.");
                dialog.setCancelable(false);
                dialog.setIndeterminate(true);
                dialog.show();
            }


            protected Void doInBackground(Void... arg0) {
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                if (dialog != null) {
                    dialog.dismiss();
                    click.setEnabled(true);
                }
            }


        };


        task.execute((Void[]) null);
    }


}