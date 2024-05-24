package mobprog.uts.fira_armelia.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NoteListResponse{

	@SerializedName("data_catatan")
	private List<DataCatatanItem> dataCatatan;

	@SerializedName("success")
	private boolean success;

	public List<DataCatatanItem> getDataCatatan(){
		return dataCatatan;
	}

	public boolean isSuccess(){
		return success;
	}
}