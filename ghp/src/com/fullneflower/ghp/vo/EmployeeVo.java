package com.fullneflower.ghp.vo;

/**値を保持する*/
public class EmployeeVo {

	/**担当者ID*/
	private String empNo;
	/**パスワード*/
	private String password;
	/**担当者*/
	private String empName;


	/**担当者IDを取得*/
	public String getEmpNo() {
		return empNo;
	}
	/**担当者IDを設定*/
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	/**パスワードを取得*/
	public String getPassword() {
		return password;
	}
	/**パスワードを設定*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**担当者名を取得*/
	public String getEmpName() {
		return empName;
	}
	/**担当者名を設定*/
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
