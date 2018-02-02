package mars.mina.mtms_heart_buffle.codec;

/**
 * 
 * <B style="color:#00f">心跳包消息对象 </B>
 * <br>
 * @author zhanglin  2017-12-18
 */
public class MtmsHeartMessageObject{
	
	
	
	
	
	
	
	
	public MtmsHeartMessageObject(String code, String timestamp,
			String termStatus, String tlvDate, String sign) {
		super();
		this.code = code;
		this.timestamp = timestamp;
		this.termStatus = termStatus;
		this.tlvDate = tlvDate;
		this.sign = sign;
	}  
	public MtmsHeartMessageObject(String code) {
		super();
		this.code = code;
	}






	/** 响应码*/
	private String code;  
	/** 时间戳 */
	private String timestamp;
	/**终端状态*/
	private String termStatus;
	/**TLV列表*/
	private String tlvDate;
	/**终端签名*/
	private String sign;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTermStatus() {
		return termStatus;
	}
	public void setTermStatus(String termStatus) {
		this.termStatus = termStatus;
	}
	public String getTlvDate() {
		return tlvDate;
	}
	public void setTlvDate(String tlvDate) {
		this.tlvDate = tlvDate;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
