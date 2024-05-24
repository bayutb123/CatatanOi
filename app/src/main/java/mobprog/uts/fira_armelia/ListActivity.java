package mobprog.uts.fira_armelia;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import mobprog.uts.fira_armelia.adapter.RecyclerAdapter;
import mobprog.uts.fira_armelia.response.DataCatatanItem;
import mobprog.uts.fira_armelia.response.NoteListResponse;
import mobprog.uts.fira_armelia.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private Service service;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        // Set the toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        // Set padding for the layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the data from the ViewModel
        mainViewModel.getNoteList();

        // Observe the data from the ViewModel
        observe();
    }

    private void observe() {
        mainViewModel.listLiveData.observe(this, data -> {
            if (data != null) {
                recyclerAdapter = new RecyclerAdapter(data);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
    }
}