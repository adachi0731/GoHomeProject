package com.fullneflower.ghp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.EmployeeVo;

/** koko */
public class EmployeeDao {
	/** コネクション */
	private Connection connection;
	/**
	 *
	 * [機 能] コンストラクタ<br>
	 * [説 明] 引数のコネクションをフィールドに設定する。<br>
	 * [備 考] なし
	 * @author 森
	 * @param connection コネクション
	 */
	public EmployeeDao(Connection connection) {
		super();
		this.connection = connection;
	}
	/**
	 * [機能]入力された社員番号とパスワードがデータベースにあるか確認する。
	 * @return 該当件数
	 * @throws GhpException
	 */
	public int search(EmployeeVo emp) throws GhpException {

		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM EMPLOYEE WHERE EMP_NO=? AND PASSWORD=?";

			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(sql);

			// SQLバインド変数への値設定
			preparedStatement.setString(1, emp.getEmpNo());
			preparedStatement.setString(2, emp.getPassword());

			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();
			int resultCnt = 0;
			while(result.next()){
				resultCnt++;
			}
			return resultCnt;
		} catch (SQLException e) {
			throw new GhpException("ghpテーブルのSELECTに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new GhpException("ステートメントの解放に失敗しました", e);
			}
		}
	}

}
