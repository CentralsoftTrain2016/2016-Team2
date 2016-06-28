package service.bean;

public class PlayerBean extends Bean {
	public PlayerBean(){
		super();
	}

	private int coolTime;
	private String itemURL = "";
	private String skillURL;


	public String getSkillURL() {
		return skillURL;
	}
	public void setSkillURL(String skillURL) {
		this.skillURL = skillURL;
	}
	public int getCoolTime() {
		return coolTime;
	}
	public void setCoolTime(int coolTime) {
		this.coolTime = coolTime;
	}
	public String getItemURL() {
		return itemURL;
	}
	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}
	public void setItemURL(){
		this.itemURL = "bgImg/ItemWindow.png";
	}

}
