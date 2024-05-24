package mobprog.uts.fira_armelia.response;

import com.google.gson.annotations.SerializedName;

public class DataCatatanItem{

	@SerializedName("detail_catatan")
	private String detailCatatan;

	@SerializedName("judul_catatan")
	private String judulCatatan;

	@SerializedName("id")
	private String id;

	@SerializedName("create_date")
	private String createDate;

	public String getDetailCatatan(){
		return detailCatatan;
	}

	public String getJudulCatatan(){
		return judulCatatan;
	}

	public String getId(){
		return id;
	}

	public String getCreateDate(){
		return createDate;
	}
}