package id.ac.ui.cs.mobileprogramming.hanifa.odoj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.Converter;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto.Ayah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto.Quran;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.dto.Surah;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.data.entity.Tilawah;
import butterknife.ButterKnife;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.APICall;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.Utils;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils.VolleyCallback;
import id.ac.ui.cs.mobileprogramming.hanifa.odoj.viewModel.TilawahViewModel;

import static android.content.Context.INPUT_METHOD_SERVICE;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewModel();

        setUpListenerButton();

        //TODO
        //Koneksi AIDL ke aplikasi alquran ketika klik start. terus jalanin quran itu.
    }

    private void setUpListenerButton() {
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
                int pageInput = Integer.parseInt(totalPageInput.getText().toString());
                if (todayTilawah != null){
                    int totalPage = todayTilawah.getJmlHalaman() + pageInput;
                    int pageRequest = totalPage;

                    if (yesterdayTilawah != null){
                        pageRequest = additionJNI(yesterdayTilawah.getPage(), totalPage);
                        System.out.println("Yesterday is not null");
                    }else{
                        System.out.println("Yesterday is null");
                    }

                    int finalTotalPage = totalPage;
                    int finalPageRequest = pageRequest;

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            APICall apiCall = new APICall(getContext());
                            apiCall.requestQuranPage(finalPageRequest, new VolleyCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    Gson gson = new GsonBuilder()
                                            .excludeFieldsWithoutExposeAnnotation()
                                            .create();
                                    Quran quranDTO = gson.fromJson(result, Quran.class);
                                    System.out.println("REQUEST API QURAN");
                                    System.out.println(quranDTO.getAyahs());
                                    Ayah lastAyah = quranDTO.getAyahs().get(quranDTO.getAyahs().size()-1);
                                    Surah surah = lastAyah.getSurah();
                                    Tilawah tilawah = new Tilawah(Utils.getDateTime(), finalTotalPage,surah.getEnglishName(),lastAyah.getJuz(),lastAyah.getNumberInSurah(),lastAyah.getPage());

                                    mViewModel.insert(tilawah);
                                }
                            });
                        }
                    });

                }else{
                    Tilawah tilawah = new Tilawah(Utils.getDateTime(),pageInput,"",0,0,0);
                    mViewModel.insert(tilawah);
                }
                System.out.println("TES");
                totalPageInput.setText("");
                Toast.makeText(getContext(), R.string.success_update, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(TilawahViewModel.class);
        mViewModel.getAllTilawah().observe(this, new AllTilawahObserver());
        mViewModel.getTodayTilawah().observe(this, new TodayTilawahObserver());
        mViewModel.getYesterdayTilawah().observe(this, new YesterdayTilawahObserver());
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

    private class TodayTilawahObserver implements Observer<Tilawah> {
        @Override
        public void onChanged(Tilawah tilawah) {
            System.out.println("CHANGED TODAY");
            if (tilawah != null){
                setTodayTilawah(tilawah);
                tilawahTodayText.setText(tilawah.toString());
                pageTodayText.setText(String.valueOf(tilawah.getJmlHalaman()));
            }
        }
    }

    private class AllTilawahObserver implements Observer<List<Tilawah>> {
        @Override
        public void onChanged(List<Tilawah> tilawahs) {
            System.out.println("CHANGED ALL]");
            System.out.println(tilawahs.size());
            for (int i = 0; i < tilawahs.size(); i++) {
                System.out.println(tilawahs.get(i));
            }
        }
    }

    private class YesterdayTilawahObserver implements Observer<Tilawah> {
        @Override
        public void onChanged(Tilawah tilawah) {
            if (tilawah != null){
                setYesterdayTilawah(tilawah);
                System.out.println(getYesterdayTilawah().getJmlHalaman());
            }
        }
    }
}
