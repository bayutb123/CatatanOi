package mobprog.uts.fira_armelia.retrofit;

import mobprog.uts.fira_armelia.request.CreateNoteRequest;
import mobprog.uts.fira_armelia.response.CreateNoteResponse;
import mobprog.uts.fira_armelia.response.NoteListResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @POST("uts/indexapipost.php")
    Call<CreateNoteResponse> createNote(
            @Body CreateNoteRequest createNoteRequest
    );

    @GET("uts/indexapi.php?req=get_catatan&nim=0102521729")
    Call<NoteListResponse> getNoteList();
}
