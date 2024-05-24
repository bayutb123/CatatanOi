package mobprog.uts.fira_armelia.response;

import com.google.gson.annotations.SerializedName;

public class CreateNoteResponse{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("success")
	private boolean success;

	public String getPesan(){
		return pesan;
	}

	public boolean isSuccess(){
		return success;
	}
}