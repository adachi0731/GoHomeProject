package com.fullneflower.ghp.vo;
/**値を保持する*/
public class ItemCategoryVo {
	/**商品カテゴリーコード*/
	private String categoryCode;
	/**商品カテゴリー名*/
	private String categoryName;
	/**説明*/
	private String categoryExplain;
	/**商品カテゴリーコードを取得*/
	public String getCategoryCode() {
		return categoryCode;
	}
	/**商品カテゴリーコード設定*/
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**商品カテゴリー名を取得*/
	public String getCategoryName() {
		return categoryName;
	}
	/**商品カテゴリー名を設定*/
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**説明を取得*/
	public String getCategoryExplain() {
		return categoryExplain;
	}
	/**説明を設定*/
	public void setCategoryExplain(String categoryExplain) {
		this.categoryExplain = categoryExplain;
	}
}
