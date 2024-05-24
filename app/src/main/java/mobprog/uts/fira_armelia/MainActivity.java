package mobprog.uts.fira_armelia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import mobprog.uts.fira_armelia.request.CreateNoteRequest;
import mobprog.uts.fira_armelia.response.CreateNoteResponse;
import mobprog.uts.fira_armelia.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private Service service;
    private String judulCatatan;
    private String isiCatatan;
    private final String nim = "0102521729";
    private final String req = "tambah_catatan";
    Button submit;
    Button lihatCatatan;
    TextView tvJudul;
    TextView tvCatatan;

    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // configuure system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // initialize the view
        tvJudul = findViewById(R.id.judulCatatan);
        tvCatatan = findViewById(R.id.isiCatatan);
        lihatCatatan = findViewById(R.id.lihatCatatan);
        lihatCatatan.setOnClickListener(this::lihatCatatan);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this::buatCatatan);

        // observe the response
        observe();
    }

    private void observe() {
        mainViewModel.responseLiveData.observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });
    }

    private void lihatCatatan(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

    private void buatCatatan(View view) {
        judulCatatan = tvJudul.getText().toString();
        isiCatatan = tvCatatan.getText().toString();
        if (judulCatatan.isEmpty() || isiCatatan.isEmpty()) {
            tvJudul.setError("Judul tidak boleh kosong");
            tvCatatan.setError("Isi catatan tidak boleh kosong");
        } else {
            mainViewModel.createNote(req, nim, judulCatatan, isiCatatan);
        }
    }
}