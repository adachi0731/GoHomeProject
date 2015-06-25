package com.fullneflower.ghp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fullneflower.ghp.exception.GhpException;
import com.fullneflower.ghp.vo.ItemAssortmentVo;
import com.fullneflower.ghp.vo.ItemCategoryVo;
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
	private static String INSERT = "insert into item(item_no,item_name,item_URL,unit_price,size,assortment_code,category_code) values(?,?,?,?,?,?,?)";

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


	//"UPDATE"という名前でSQL文を作成
	private static String UPDATE ="UPDATE ITEM SET ITEM_NAME=?, " +
			"ITEM_URL=?, UNIT_PRICE=?, SIZE=?, " +
			"ASSORTMENT_CODE=?, CATEGORY_CODE=? " +
			"WHERE ITEM_NO=?";
	/* [備考]なし
	 * @author 足立
	 * @param itemV
	 * @return 更新件数
	 * @throws GhpException
	 */
	public int update(ItemVo itemV) throws GhpException{

		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(UPDATE);

			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemV.getItemName());
			preparedStatement.setString(2, itemV.getItemURL());
			preparedStatement.setInt(3, itemV.getUnitPrice());
			preparedStatement.setString(4, itemV.getSize());
			preparedStatement.setString(5, itemV.getAssortmentCode());
			preparedStatement.setString(6, itemV.getCategoryCode());
			preparedStatement.setString(7, itemV.getItemNo());


			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;

		} catch (SQLException e) {
			throw new GhpException("ghpテーブルのUPDATEに失敗しました", e);
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


	/**
	 * [機能]データベース上のフラグに"1"がある商品番号とフラグを確認するメソッド
	 * [説明]データベース上の商品番号とフラグを確認し、その商品番号をItemVoに詰める
	 * @author 森
	 * @param itemNo
	 * @return item
	 */
	// SQLの定義
	private static String DELETECHECK = "SELECT item_no FROM ITEM WHERE item_no=? and active_flg ='1'";
	//publicの後ろは～型の返り値を返す
	public ItemVo deleteCheck(String itemNo) throws GhpException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(DELETECHECK);
			// SQLバインド変数への値設定
			preparedStatement.setString(1,itemNo);
			// SQLの実行
			resultSet = preparedStatement.executeQuery();
			//sqlから取得した値をVoに詰める
			ItemVo item = new ItemVo();
			while(resultSet.next()) {
				item.setItemNo(resultSet.getString("item_no"));
			}
			return item;
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

	/**
	 * [機能]
	 * [説明]
	 * @author 森
	 * @param itemNo
	 * @return item
	 */
	private static String DELETESelect = "SELECT i.item_no,i.item_name,i.unit_price,i.size,a.assortment_name,c.category_name "+
			"FROM item i INNER JOIN item_assortment a ON i.assortment_code=a.assortment_code INNER JOIN item_category c ON i.category_code=c.category_code" +
			" INNER JOIN item_stock s ON i.item_no=s.item_no WHERE i.item_no=? and s.stock=0;";

	public ItemVo deleteSelect(String itemNo)throws GhpException {
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(DELETESelect);

			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemNo);
			System.out.println(preparedStatement);
			// SQLの実行
			ResultSet result = preparedStatement.executeQuery();

			ItemVo item = new ItemVo();
			ItemAssortmentVo itemAss = new ItemAssortmentVo();
			ItemCategoryVo itemCat = new ItemCategoryVo();
			while(result.next()) {
				item.setItemNo(result.getString(1));
				item.setItemName(result.getString(2));
				item.setUnitPrice(result.getInt(3));
				item.setSize(result.getString(4));
				itemAss.setAssortmentName(result.getString(5));
				item.setAssortVo(itemAss);
				itemCat.setCategoryName(result.getString(6));
				item.setCateVo(itemCat);
				System.out.println(item);
			}
			return item;
		} catch (SQLException e) {
			System.out.println("ItemDaoでエラー");
			throw new GhpException("ghpテーブルのSELECTに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				System.out.println("ItemDaoでエラー");
				e.printStackTrace();
				throw new GhpException("ステートメントの解放に失敗しました", e);
			}
		}
	}
	private static String DELETE = "UPDATE ITEM SET active_flg=0 WHERE item_no=?";

	public int delete(String itemNo)throws GhpException {
		PreparedStatement preparedStatement = null;
		try {
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(DELETE);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, itemNo);
			System.out.println(preparedStatement);
			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
		}catch(SQLException e){
			throw new GhpException("ghpテーブルのDELETE(論理削除)に失敗しました", e);
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				System.out.println("ItemDaoでエラー");
				e.printStackTrace();
				throw new GhpException("ステートメントの解放に失敗しました", e);
			}
		}
	}

	/**
	 * クエリ文字列
	 */
	//private static String SELECT_ALL = "SELECT * FROM ITEM ORDER BY ITEM_NO ASC";
	private static String SELECT_ALL = "SELECT i.item_no,i.item_name," +
			"							i.unit_price,i.size,a.assortment_name,c.category_name " +
			"				FROM item i INNER JOIN item_assortment a ON " +
			"				i.assortment_code=a.assortment_code INNER JOIN item_category c ON " +
			"				i.category_code=c.category_code WHERE i.active_flg='1' ORDER BY ITEM_NO ASC";



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
				itemVo.setAssortmentCode(resultSet.getString("ASSORTMENT_NAME"));
				itemVo.setCategoryCode(resultSet.getString("CATEGORY_NAME"));
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
	private static String INSERTCHECK = "SELECT * FROM ITEM WHERE ITEM_NO = ? OR ITEM_NAME = ?";


	//publicの後ろは～型の返り値を返す
	public List<ItemVo> insertCheck(ItemVo itemVo) throws GhpException {
		//System.out.println(iNo);

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(INSERTCHECK);
			// SQLバインド変数への値設定
			preparedStatement.setString(1,itemVo.getItemNo());
			preparedStatement.setString(2,itemVo.getItemName());
			// SQLの実行
			resultSet = preparedStatement.executeQuery();
			//sqlから取得した値をVoに詰める
			List<ItemVo> deleteCheck = new ArrayList<ItemVo>();
			while (resultSet.next()) {

				itemVo.setItemName(resultSet.getString("ACTIVE_FLG"));
				deleteCheck.add(itemVo);
			}
			return deleteCheck;
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


	// SQLの定義
	private static String NAMECHECK = "select * from item WHERE ITEM_NO!=? AND ITEM_NAME=?;";


	//publicの後ろは～型の返り値を返す
	public boolean nameCheck(String itemNo,String itemName) throws GhpException {
		//System.out.println(iNo);

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			// SQLの作成(準備)
			preparedStatement = connection.prepareStatement(NAMECHECK);
			// SQLバインド変数への値設定
			preparedStatement.setString(1,itemNo);
			preparedStatement.setString(2,itemName);
			// SQLの実行
			resultSet = preparedStatement.executeQuery();
			//sqlから取得した値をVoに詰める
			if(resultSet.next()) {
				return false;
			}
			return true;
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


	private static String ASSORTMENT = "select assortment_code,assortment_name,explain from item_assortment ";
	//"SELECT * FROM ITEM ORDER BY ITEM_NO ASC";
	/**
	 *
	 * [機 能] 全件参照するメソッド<br>
	 * [説 明] 部署テーブルの全件を参照する。<br>
	 * [備 考] なし
	 * @return
	 */
	public List<ItemAssortmentVo> assortment() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(ASSORTMENT);
			resultSet = preparedStatement.executeQuery();
			List<ItemAssortmentVo> assortmentList = new ArrayList<ItemAssortmentVo>();
			while (resultSet.next()) {
				ItemAssortmentVo assortmentVo = new ItemAssortmentVo();
				assortmentVo.setAssortmentCode(resultSet.getString("ASSORTMENT_CODE"));
				assortmentVo.setAssortmentName(resultSet.getString("ASSORTMENT_NAME"));
				assortmentVo.setAssortmentExplain(resultSet.getString("EXPLAIN"));
				assortmentList.add(assortmentVo);
			}
			return assortmentList;
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
	private static String CATEGORY = "select category_code,category_name,explain from item_category ";
	//"SELECT * FROM ITEM ORDER BY ITEM_NO ASC";
	/**
	 *
	 * [機 能] 全件参照するメソッド<br>
	 * [説 明] 部署テーブルの全件を参照する。<br>
	 * [備 考] なし
	 * @return
	 */
	public List<ItemCategoryVo> category() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(CATEGORY);
			resultSet = preparedStatement.executeQuery();
			List<ItemCategoryVo> categoryList = new ArrayList<ItemCategoryVo>();
			while (resultSet.next()) {
				ItemCategoryVo categoryVo = new ItemCategoryVo();
				categoryVo.setCategoryCode(resultSet.getString("CATEGORY_CODE"));
				categoryVo.setCategoryName(resultSet.getString("CATEGORY_NAME"));
				categoryVo.setCategoryExplain(resultSet.getString("EXPLAIN"));
				categoryList.add(categoryVo);

			}
			return categoryList;
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
			private static String DOUBLE = "SELECT ITEM_NO,ITEM_NAME,ITEM_URL,UNIT_PRICE,SIZE,CATEGORY_CODE,ASSORTMENT_CODE FROM ITEM WHERE ITEM_NO=? OR ITEM_NAME = ?";


			//publicの後ろは～型の返り値を返す
			public boolean Double(String iNo,String iName) throws GhpException {
				//System.out.println(iNo);

				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try{
					// SQLの作成(準備)
					preparedStatement = connection.prepareStatement(DOUBLE);
					// SQLバインド変数への値設定
					preparedStatement.setString(1,iNo);
					preparedStatement.setString(2,iName);
					// SQLの実行
					resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {
						return false;
					}
					return true;
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

			// SQLの定義
			private static String SELECT_POINT = "SELECT ITEM_NAME,ITEM_URL,UNIT_PRICE,SIZE,CATEGORY_CODE,ASSORTMENT_CODE FROM ITEM WHERE ITEM_NO=?";


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
						itemVo.setAssortmentCode(resultSet.getString("assortment_code"));
						itemVo.setCategoryCode(resultSet.getString("category_code"));

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
