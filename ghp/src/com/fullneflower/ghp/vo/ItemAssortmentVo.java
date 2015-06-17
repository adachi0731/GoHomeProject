package com.fullneflower.ghp.vo;
/**値を保持する*/
public class ItemAssortmentVo {
	/**商品種別コード*/
	private String assortmentCode;
	/**商品種別名*/
	private String assortmentName;
	/**説明*/
	private String assortmentExplain;
	/**商品種別コードを取得*/
	public String getAssortmentCode() {
		return assortmentCode;
	}/**商品種別コードを設定*/
	public void setAssortmentCode(String assortmentCode) {
		this.assortmentCode = assortmentCode;
	}/**商品種別名を取得*/
	public String getAssortmentName() {
		return assortmentName;
	}/**商品種別名を設定*/
	public void setAssortmentName(String assortmentName) {
		this.assortmentName = assortmentName;
	}/**説明を取得*/
	public String getAssortmentExplain() {
		return assortmentExplain;
	}/**説明を設定*/
	public void setAssortmentExplain(String assortmentExplain) {
		this.assortmentExplain = assortmentExplain;
	}
}
