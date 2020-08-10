package User;

public class UserVO {
	private int userId;
	private int userSex;
	private int userAge;
	private double userHeight;
	private int STONE;
	
	public UserVO(int userId, int userSex, int userAge, double userHeight, int STONE) {
		this.userId = userId;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userHeight = userHeight;
		this.STONE = STONE;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public double getUserHeight() {
		return userHeight;
	}
	public void setUesrHeight(double uesrHeight) {
		this.userHeight = uesrHeight;
	}
	
	public int getSTONE() {
		return STONE;
	}
	
}
