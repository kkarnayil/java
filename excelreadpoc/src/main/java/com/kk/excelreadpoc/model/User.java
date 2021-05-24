/**
 * 
 */
package com.kk.excelreadpoc.model;

/**
 * @author kartikkarnayil
 *
 */
public class User {

	private String aadhar;

	private String refNum;

	private String uscp;

	/**
	 * @param aadhar
	 * @param refNum
	 * @param uscp
	 */
	public User(String aadhar, String refNum, String uscp) {
		super();
		this.aadhar = aadhar;
		this.refNum = refNum;
		this.uscp = uscp;
	}

	/**
	 * @return the aadhar
	 */
	public String getAadhar() {
		return aadhar;
	}

	/**
	 * @return the refNum
	 */
	public String getRefNum() {
		return refNum;
	}

	/**
	 * @return the uscp
	 */
	public String getUscp() {
		return uscp;
	}

	@Override
	public String toString() {
		return "User [" + (aadhar != null ? "aadhar=" + aadhar + ", " : "")
				+ (refNum != null ? "refNum=" + refNum + ", " : "") + (uscp != null ? "uscp=" + uscp : "") + "]";
	}

}
