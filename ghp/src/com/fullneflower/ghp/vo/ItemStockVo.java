package com.fullneflower.ghp.vo;
/**値を保持する*/
public class ItemStockVo {
	/**商品番号*/
	private int itemNo;
	/**在庫数*/
	private int stock;
	/**商品番号を取得*/
	public int getItemNo() {
		return itemNo;
	}/**商品番号を設定*/
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}/**在庫数を取得*/
	public int getStock() {
		return stock;
	}/**在庫数を設定*/
	public void setStock(int stock) {
		this.stock = stock;
	}

}
