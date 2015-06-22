package com.fullneflower.ghp.vo;

/**値を保持する*/
public class ItemVo {
	/**商品番号*/
	private String itemNo;
	/**商品名*/
	private String itemName;
	/**商品画像URL*/
	private String itemURL;
	/**単価*/
	private int unitPrice;
	/**寸法*/
	private String size;
	/**商品種別コード*/
	private String assortmentCode;//つめて
	/**商品カテゴリーコード*/
	private String categoryCode;
	/**商品カテゴリークラス*/
	private ItemCategoryVo categoryVo;
	/** 商品在庫クラス*/
	private ItemStockVo stockVo;
	/** 商品種別クラス*/
	private ItemAssortmentVo assortmentVo;//がいとうNoをかえす

	public ItemAssortmentVo getAssortmentVo() {
		return assortmentVo;
	}
	public void setAssortVo(ItemAssortmentVo assortmentVo) {
		this.assortmentVo = assortmentVo;
	}
	public ItemStockVo getStockVo() {
		return stockVo;
	}
	public void setStockVo(ItemStockVo stockVo) {
		this.stockVo = stockVo;
	}
	public ItemCategoryVo getItmCtVo() {
		return categoryVo;
	}
	public void setItmCtVo(ItemCategoryVo categoryVo) {
		this.categoryVo = categoryVo;
	}


	/**商品番号を取得*/
	public String getItemNo() {
		return itemNo;
	}
	/**商品番号を設定*/
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	/**商品名を取得*/
	public String getItemName() {
		return itemName;
	}
	/**商品名を設定*/
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**商品画像URLを取得*/
	public String getItemURL() {
		return itemURL;
	}
	/**商品画像URLを設定*/
	public void setItemURL(String itemURL) {
		this.itemURL = itemURL;
	}
	/**単価を取得*/
	public int getUnitPrice() {
		return unitPrice;
	}
	/**単価を設定*/
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**寸法を取得*/
	public String getSize() {
		return size;
	}
	/**寸法を設定*/
	public void setSize(String size) {
		this.size = size;
	}
	/**商品種別コードを取得*/
	public String getAssortmentCode() {
		return assortmentCode;
	}
	/**商品種別コードを設定*/
	public void setAssortmentCode(String assortmentCode) {
		this.assortmentCode = assortmentCode;
	}
	/**商品カテゴリーコードを取得*/
	public String getCategoryCode() {
		return categoryCode;
	}
	/**商品カテゴリーコードを設定*/
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
