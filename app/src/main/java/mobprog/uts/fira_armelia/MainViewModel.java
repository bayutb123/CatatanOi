package mobprog.uts.fira_armelia;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mobprog.uts.fira_armelia.request.CreateNoteRequest;
import mobprog.uts.fira_armelia.response.CreateNoteResponse;
import mobprog.uts.fira_armelia.response.DataCatatanItem;
import mobprog.uts.fira_armelia.response.NoteListResponse;
import mobprog.uts.fira_armelia.retrofit.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://103.178.153.230/").build();
    Service service = retrofit.create(Service.class);

    public MutableLiveData<List<DataCatatanItem>> listLiveData = new MutableLiveData<>();
    public MutableLiveData<String> responseLiveData = new MutableLiveData<>();

    public void createNote(String req, String nim, String title, String content) {
        CreateNoteRequest createNoteRequest = new CreateNoteRequest(req, nim, title, content);
        service.createNote(createNoteRequest).enqueue(new Callback<CreateNoteResponse>() {
            @Override
            public void onResponse(Call<CreateNoteResponse> call, Response<CreateNoteResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("MainViewModel", "onResponse: " + response.body().getPesan());
                        responseLiveData.setValue(response.body().getPesan());
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateNoteResponse> call, Throwable t) {
                Log.e("MainViewModel", "onFailure: ", t);
                responseLiveData.setValue(t.getMessage());
            }
        });
    }

    public void getNoteList() {
        service.getNoteList().enqueue(new Callback<NoteListResponse>() {
            @Override
            public void onResponse(Call<NoteListResponse> call, Response<NoteListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listLiveData.setValue(response.body().getDataCatatan());
                    }
                }
            }

            @Override
            public void onFailure(Call<NoteListResponse> call, Throwable t) {
                Log.e("MainViewModel", "onFailure: ", t);
            }
        });
    }
}
