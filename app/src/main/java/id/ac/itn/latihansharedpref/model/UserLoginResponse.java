package id.ac.itn.latihansharedpref.model;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponse{

	@SerializedName("data")
	private UserLoginModel data;

	@SerializedName("status")
	private boolean status;

	public void setData(UserLoginModel data){
		this.data = data;
	}

	public UserLoginModel getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}