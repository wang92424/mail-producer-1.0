package com.bhz.mail.enumeration;
/****
 * 
 * <p>Title:MailStatus</p>
 * <p>Description: 邮件发送状态</p>
 * <p>Company: </p>
 * @author MrWang
 * @date 2018年3月15日 下午10:56:11
 */
public enum MailStatus {

	/**暂存/待发送*/
	DRAFT("0"),
	/**发送中*/
	SEND_IN("1"),
	/** 发送成功 */
	NEED_OK("2"),
	/** 发送失败 */
	NEED_ERROR("3");
	
	private String code;
	
	private MailStatus(String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
