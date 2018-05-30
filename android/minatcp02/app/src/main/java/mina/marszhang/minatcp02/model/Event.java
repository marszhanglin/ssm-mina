package mina.marszhang.minatcp02.model;

/**
 * 
 * <B style="color:#00f"> 应用安装事件中上送的event</B>
 * <br>
 * @author zhanglin  2018-5-14
 */
public class Event {
	
	/** 事件类型 */
	private String type;
	/** 动作 */
	private String action;
	/** 应用包名 */
	private String pkgName;
	/** 应用版本号 */
	private String versionCode;
	/** 应用版本名 */
	private String versionName;
	/** 事件时间 */
	private String actionTime;
	/** 应用权限 */
	private String apkPermission;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getApkPermission() {
		return apkPermission;
	}
	public void setApkPermission(String apkPermission) {
		this.apkPermission = apkPermission;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}


	@Override
	public String toString() {
		return "Event{" +
				"type='" + type + '\'' +
				", action='" + action + '\'' +
				", pkgName='" + pkgName + '\'' +
				", versionCode='" + versionCode + '\'' +
				", versionName='" + versionName + '\'' +
				", actionTime='" + actionTime + '\'' +
				", apkPermission='" + apkPermission + '\'' +
				'}';
	}
}