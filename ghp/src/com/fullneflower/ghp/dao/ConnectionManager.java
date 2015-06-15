package com.fullneflower.ghp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 *
 * [機 能] データベースの接続と切断ドライバークラス<br>
 * [説 明] データベースの接続と切断を行う。<br>
 * [備 考] なし<br>
 * [環 境] JavaSE 6.0 <br>
 * Copyright(c) 2013 Fullness, Inc. All Rights Reserved
 * @author [作 成] 2013/03/05 fullness(fullness)
 */
public class ConnectionManager {
	/**
	 * コネクション
	 */
	private Connection connection;

	/**
	 * 接続ドライバー名
	 */
	private final String DRIVER_NAME ;

	/**
	 * 接続URL
	 */
	private final String URL ;

	/**
	 * 接続ユーザ
	 */
	private final String USER;

	/**
	 * 接続パスワード
	 */
	private final String PASSWORD ;

	//public static void main(String[] args) {
		//ConnectionManager cm = new ConnectionManager();
		//cm.getConnection();
		//System.out.println("OKです");
	//}
	/**
	 * コンストラクタ
	 */
		public ConnectionManager(){
			ResourceBundle rBundle = ResourceBundle.getBundle("Db");
			DRIVER_NAME=rBundle.getString("DRIVER_NAME");
			URL =rBundle.getString("URL");
			USER=rBundle.getString("USER");
			PASSWORD=rBundle.getString("PASSWORD");

		}


	/**
	 *
	 * [機 能] コネクション取得メソッド<br>
	 * [説 明] コネクションを取得し、返却する。<br>
	 *        ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 * @return コネクション
	 */
	public Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			System.out.println("ドライバーのロードに成功しました");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ドライバーのロードに失敗しました", e);
		}
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				//コミットモードの設定
				connection.setAutoCommit(false);
				System.out.println("データベースの接続に成功しました");
			} catch (SQLException e) {
				throw new RuntimeException("データベースの接続に失敗しました", e);
			}
		}
		return connection;
	}

	/**
	 *
	 * [機 能] <br>
	 * [説 明] コネクションを切断する。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 */
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("データベースの切断に成功しました");
			}
		} catch (SQLException e) {
			throw new RuntimeException("データベースの切断に失敗しました", e);
		}
	}

	/**
	 *
	 * [機 能] コミットメソッド<br>
	 * [説 明] トランザクションをコミットする。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 */
	public void commit() {
		try {
			if (connection != null) {
				connection.commit();
				System.out.println("トランザクションのコミットに成功しました");
			}
		} catch (SQLException e) {
			throw new RuntimeException("トランザクションのコミットに失敗しました", e);
		}
	}

	/**
	 *
	 * [機 能] <br>
	 * [説 明] トランザクションをロールバックする。<br>
	 *         ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 */
	public void rollback() {
		try {
			if (connection != null) {
				connection.rollback();
				System.out.println("トランザクションのロールバックに成功しました");
			}
		} catch (SQLException e) {
			throw new RuntimeException("トランザクションのロールバックに失敗しました", e);
		}
	}

}
