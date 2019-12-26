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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.Converter;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;
import butterknife.ButterKnife;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.Utils;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel.TilawahViewModel;

public class TilawahFragment extends Fragment {

    static {
        System.loadLibrary("addition-lib");
    }

    private TilawahViewModel mViewModel;
    private Integer seconds;

    @BindView(R.id.startButton)
    Button startButton;

    @BindView(R.id.stopButton)
    Button stopButton;

    @BindView(R.id.tilawah_today_value)
    TextView tilawahTodayText;

    @BindView(R.id.pge_tilawah_value)
    TextView pageTodayText;

    @BindView(R.id.resumeButton)
    Button resumeButton;

    @BindView(R.id.timer)
    TextView timer;

    @BindView(R.id.saveButton)
    Button saveButton;

    @BindView(R.id.totalPageInput)
    EditText totalPageInput;

    AsyncStopwatch asyncStopwatch;

    private Tilawah yesterdayTilawah;
    private Tilawah todayTilawah;

    public Tilawah getTodayTilawah() {
        return todayTilawah;
    }

    public void setTodayTilawah(Tilawah todayTilawah) {
        this.todayTilawah = todayTilawah;
    }

    public Tilawah getYesterdayTilawah() {
        return yesterdayTilawah;
    }

    public void setYesterdayTilawah(Tilawah yesterdayTilawah) {
        this.yesterdayTilawah = yesterdayTilawah;
    }


    public static TilawahFragment newInstance() {
        return new TilawahFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tilawah_fragment, container, false);
        ButterKnife.bind(this, view);

        System.out.println("FROM JNI");
        System.out.println(additionJNI(2,5));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TilawahViewModel.class);
        mViewModel.getAllTilawah().observe(this, new Observer<List<Tilawah>>() {
            @Override
            public void onChanged(List<Tilawah> tilawahs) {
                System.out.println("CHANGED ALL]");
                System.out.println(tilawahs.size());
                for (int i=0;i<tilawahs.size();i++){
                    System.out.println(tilawahs.get(i));
                }
            }
        });

        mViewModel.getTodayTilawah().observe(this, new Observer<Tilawah>() {
            @Override
            public void onChanged(Tilawah tilawah) {
                System.out.println("CHANGED TODAY");
                if (tilawah != null){
                    setTodayTilawah(tilawah);
                    tilawahTodayText.setText(tilawah.toString());
                    pageTodayText.setText(String.valueOf(tilawah.getJmlHalaman()));
                }
            }
        });

        mViewModel.getYesterdayTilawah().observe(this, new Observer<Tilawah>() {
            @Override
            public void onChanged(Tilawah tilawah) {
                if (tilawah != null){
                    setYesterdayTilawah(tilawah);
                    System.out.println(getYesterdayTilawah().getJmlHalaman());
                }
            }
        });

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
                int totalPage=0;
                if (todayTilawah != null){
                    if (yesterdayTilawah != null){
//                        TODO get data from yesterday
                        System.out.println("Yesterday is not null");
                    }else{
                        System.out.println("Yesterday null");
                    }

                    totalPage = additionJNI( todayTilawah.getJmlHalaman(),Integer.parseInt(totalPageInput.getText().toString()));
                }else{
                    totalPage = additionJNI( 0 ,Integer.parseInt(totalPageInput.getText().toString()));
                }
                Tilawah tilawah = new Tilawah(Utils.getDateTime(),totalPage,"",0,0,0);
                mViewModel.insert(tilawah);
                System.out.println("TES");
            }
        });
        //TODO
        //Koneksi AIDL ke aplikasi alquran ketika klik start. terus jalanin quran itu.
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

    public native int additionJNI(int left, int right);
}
