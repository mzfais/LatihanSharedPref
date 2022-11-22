package id.ac.itn.latihansharedpref.model;

import com.google.gson.annotations.SerializedName;

public class UserLoginModel {

	@SerializedName("user_mail")
	private String userMail;

	@SerializedName("role_id")
	private String roleId;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("id_user")
	private String idUser;

	public UserLoginModel(String idUser,String userName, String userMail, String roleId ) {
		this.userMail = userMail;
		this.roleId = roleId;
		this.userName = userName;
		this.idUser = idUser;
	}

	public void setUserMail(String userMail){
		this.userMail = userMail;
	}

	public String getUserMail(){
		return userMail;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	@Override
	public String toString() {
		return "UserLoginModel{" +
				"userMail='" + userMail + '\'' +
				", userName='" + userName + '\'' +
				", idUser='" + idUser + '\'' +
				'}';
	}
}