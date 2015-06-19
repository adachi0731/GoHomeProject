package com.fullneflower.ghp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemVo;

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
	public int insert(ItemVo itemVo) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1, itemVo.getItemNo());
			preparedStatement.setString(2, itemVo.getItemName());
			preparedStatement.setString(3, itemVo.getItemURL());
			preparedStatement.setInt(4, itemVo.getUnitPrice());
			preparedStatement.setString(5, itemVo.getSize());
			preparedStatement.setString(6, itemVo.getAssortmentCode());
			preparedStatement.setString(7, itemVo.getCategoryCode());
			int result = preparedStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
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
	private static String SELECT_ALL = "SELECT * FROM ITEM ORDER BY ITEM_NO ASC";
	/*
	 * private static String SELECT_ALL = "SELECT i.item_no,i.item_name," +
			"							i.unit_price,i.size,a.assortment_name,c.category_name " +
			"				FROM item i INNER JOIN item_assortment a ON " +
			"				i.assortment_code=a.assortment_code INNER JOIN item_category c ON " +
			"				i.category_code=c.category_code";

	 */

	/**
	 *
	 * [機 能] 全件参照するメソッド<br>
	 * [説 明] 部署テーブルの全件を参照する。<br>
	 * [備 考] なし
	 * @return
	 */
	public List<ItemVo> selectAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(SELECT_ALL);
			resultSet = preparedStatement.executeQuery();
			List<ItemVo> itemList = new ArrayList<ItemVo>();
			while (resultSet.next()) {
				ItemVo itemVo = new ItemVo();
				itemVo.setItemNo(resultSet.getString("ITEM_NO"));
				itemVo.setItemName(resultSet.getString("ITEM_NAME"));
				itemVo.setUnitPrice(resultSet.getInt("UNIT_PRICE"));
				itemVo.setSize(resultSet.getString("SIZE"));
				itemVo.setAssortmentCode(resultSet.getString("ASSORTMENT_CODE"));
				itemVo.setCategoryCode(resultSet.getString("CATEGORY_CODE"));
				itemList.add(itemVo);
			}
			return itemList;
		 } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } finally {
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	            try {
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	        }
	}


	// SQLの定義
	private static String SELECT_POINT = "SELECT ITEM_NAME,ITEM_URL,UNIT_PRICE,SIZE FROM ITEM WHERE ITEM_NO=?";


	//publicの後ろは～型の返り値を返す
	public List<ItemVo> selectPoint(String iNo) throws GhpException {
		//System.out.println(iNo);

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(SELECT_POINT);
			// SQLバインド変数への値設定
			preparedStatement.setString(1,iNo);
			// SQLの実行
			resultSet = preparedStatement.executeQuery();
			//sqlから取得した値をVoに詰める
			List<ItemVo> itemList = new ArrayList<ItemVo>();
			ItemVo itemVo = new ItemVo();
			while (resultSet.next()) {

				itemVo.setItemName(resultSet.getString("ITEM_NAME"));
				itemVo.setItemURL(resultSet.getString("ITEM_URL"));
				itemVo.setUnitPrice(resultSet.getInt("UNIT_PRICE"));
				itemVo.setSize(resultSet.getString("SIZE"));
				//itemVo.setAssortmentCode(resultSet.getString("ASSORTMENT"));
				//itemVo.setCategoryCode(resultSet.getString("CATEGORY"));

				itemList.add(itemVo);
			}
			return itemList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GhpException("ステートメントの解放に失敗しました", e);
		}finally{
			try{
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
