package com.fullneflower.ghp.dao;

import java.sql.Connection;

public class ItemDao {
	/** コネクション */
	private Connection connection;
	/**
	 *
	 * [機 能] コンストラクタ<br>
	 * [説 明] 引数のコネクションをフィールドに設定する。<br>
	 * [備 考] なし
	 * @param connection コネクション
	 */
	public ItemDao(Connection connection) {
		super();
		this.connection = connection;
	}



	/**
	 * クエリ文字列
	 */
	private static String INSERT = "";

	/**
	 *
	 * [機 能] レコードを登録するメソッド<br>
	 * [説 明] 引数の部署データを部署テーブルに登録する。<br>
	 * [備 考] なし
	 * @param
	 * @return 登録件数
	 */
	public int insert() {
		int result = 0;
		return result;
	}

	private static String UPDATE = "";

	public int update() {
		int result = 0;
		return result;
	}


	private static String DELETE = "";

	public int delete() {
		int result = 0;
		return result;
	}

	/**
	 * クエリ文字列
	 */
	private static String SELECT_ALL = "";

	/**
	 *
	 * [機 能] 全件参照するメソッド<br>
	 * [説 明] 部署テーブルの全件を参照する。<br>
	 * [備 考] なし
	 * @return
	 */
	public void selectAll() {

	}

	private static String SELECT_POINT = "";

	public void selectPoint() {

	}
}
