package koalaTest.user.model.vo;

public class User {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userBirth;
	private String userPhone;
	private String userEmail;
	private String userAddr1;
	private String userAddr2;
	private String userState; 
	private int userLevel;
	private String enrollDate;
	private String randomCode;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userId,String userPw, String randomCode) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.randomCode = randomCode;
	}
	

	public User(int userNo, String userId, String userPw, String userName, String userBirth, String userPhone,
			String userEmail, String userAddr1, String userAddr2, String userState, int userLevel, String enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAddr1 = userAddr1;
		this.userAddr2 = userAddr2;
		this.userState = userState;
		this.userLevel = userLevel;
		this.enrollDate = enrollDate;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAddr1() {
		return userAddr1;
	}
	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}
	public String getUserAddr2() {
		return userAddr2;
	}
	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userBirth=" + userBirth + ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", userAddr1="
				+ userAddr1 + ", userAddr2=" + userAddr2 + ", userState=" + userState + ", userLevel=" + userLevel
				+ ", enrollDate=" + enrollDate + "]";
	}
	
}
