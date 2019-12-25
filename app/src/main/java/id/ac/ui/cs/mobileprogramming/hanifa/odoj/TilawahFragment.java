package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.PageTilawah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.entity.Tilawah;
import butterknife.ButterKnife;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel.TilawahViewModel;

public class TilawahFragment extends Fragment {

    private TilawahViewModel mViewModel;
    private Integer seconds;

    @BindView(R.id.startButton)
    Button startButton;

    @BindView(R.id.stopButton)
    Button stopButton;

    @BindView(R.id.resumeButton)
    Button resumeButton;

    @BindView(R.id.timer)
    TextView timer;

    @BindView(R.id.saveButton)
    Button saveButton;

    @BindView(R.id.totalPageInput)
    EditText totalPageInput;

    AsyncStopwatch asyncStopwatch;

    public static TilawahFragment newInstance() {
        return new TilawahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tilawah_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TilawahViewModel.class);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch = new AsyncStopwatch();
                seconds = 0;
                asyncStopwatch.execute(seconds);
                startButton.setEnabled(false);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch.cancel(true);
                startButton.setEnabled(true);
                resumeButton.setEnabled(true);
            }
        });
        resumeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                asyncStopwatch = new AsyncStopwatch();
                asyncStopwatch.execute(seconds);
                resumeButton.setEnabled(false);
                startButton.setEnabled(false);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tilawah tilawah = new Tilawah(getDateTime(),Integer.parseInt(totalPageInput.getText().toString()),new PageTilawah(1,1,"dummy",1));
                mViewModel.insert(tilawah);
            }
        });
        //TODO
        //Koneksi AIDL ke aplikasi alquran ketika klik start. terus jalanin quran itu.
    }

    private java.sql.Date getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return java.sql.Date.valueOf(dateFormat.format(date));
    }

    private class AsyncStopwatch extends AsyncTask<Integer, String, Integer> {
        @Override
        protected Integer doInBackground(Integer... integers) {
            System.out.println("Started");
            while (!isCancelled()){
                publishProgress(Integer.toString(seconds));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds = seconds+1;
                System.out.println(seconds);
            }
            return seconds;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            timer.setText(progress[0]);
        }
    }

}
