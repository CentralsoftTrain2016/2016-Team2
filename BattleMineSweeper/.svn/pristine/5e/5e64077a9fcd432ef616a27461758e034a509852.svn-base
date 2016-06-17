package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.DifficultyDao;
import dao.MassDao;
import domain.Difficulty;
import domain.Mass;
import domain.value.AROUNDMINES;
import domain.value.GAMEID;
import domain.value.ISEXISTMINE;
import domain.value.ISOPEN;
import domain.value.ITEMID;
import domain.value.MASSID;
import domain.value.QUIZID;

public class FieldService extends Service{

	// ゲームのマス生成
	public void fieldPlacement(int gameid,int difficultyid ) {
		final int HEIGHT = 16;  // 縦の数
		final int SIDE = 16;    // 横の数
		final int MASSFIRSTNUM = 1; // マス番号の初め
		final int MASSLASTNUM = HEIGHT*SIDE;  // マス番号の終わり
		List<Mass>masslist = new ArrayList<Mass>();

		try{
		// 初期状態の mass を生成
		for (int i = 1 ; i <= MASSLASTNUM ; i++){
			Mass mass = new Mass();
			mass.setMASSID(new MASSID(i));
			mass.setGAMEID(new GAMEID(gameid));
			mass.setISEXISTMINE(new ISEXISTMINE(false));
			mass.setAROUNDMINES(new AROUNDMINES(0));
			mass.setISOPEN(new ISOPEN(false));
			mass.setITEMID(new ITEMID(0));
			mass.setQUIZID(new QUIZID(0));

			// リストに格納
			masslist.add(mass);
		}
		System.out.println(con.isClosed());


		// 爆弾があるマスIDをリストで生成する。
		List<Integer>minelist = new ArrayList<Integer>();
		minelist = minePlacement(difficultyid,MASSLASTNUM);
		// 確認用
		// System.out.println(minelist);

		// 地雷の有無をセットする。
		System.out.println(con.isClosed());

		for (int j = 0 ; j < minelist.size() ; j++){
			masslist.get(minelist.get(j)-1).setISEXISTMINE(new ISEXISTMINE(true));
		}
		System.out.println(con.isClosed());

		// 周りの地雷の数を数えてセットする。

		int minesum =0;    // 爆弾の数
		int xPoint = 0;    // x座標
		int yPoint = 0 ;   // y座標

		for(int i = MASSFIRSTNUM ; i <= MASSLASTNUM ; i++){
			minesum = 0;   // 初期は0にセット
			xPoint = (i-1)%SIDE;    // x座標のセット
			yPoint = (i-1)/HEIGHT;  // y座標のセット

			// 確認用
//			System.out.println(i);
//			System.out.println(xPoint);
//			System.out.println(yPoint);


			// 自身のマスを含む周囲9マスの地雷の確認
			for(int x = 0 ; x < 3  ; x++){
				if((xPoint-1+x)<0 ||(xPoint-1+x)>=SIDE )continue;
				for(int y = 0 ; y < 3 ; y++){
					if((yPoint-1+y)<0 || (yPoint-1+y)>=HEIGHT)continue;
					// 地雷かどうかは確認
					if(masslist.get((yPoint -1 + y)*SIDE + (xPoint-1 + x)).getISEXISTMINE().get()){
						// 地雷の数を1追加する
						minesum++;
					}
				}
			}

			// 自身のマスを含む周囲9マスの地雷の数をセット
			masslist.get(i-1).setAROUNDMINES(new AROUNDMINES(minesum));



			// 確認用
			if(masslist.get(i-1).getISEXISTMINE().get()){
				System.out.print("*");
			}
			else if(masslist.get(i-1).getAROUNDMINES().get()==0){
				System.out.print(" ");
			}
			else{
				System.out.print(masslist.get(i-1).getAROUNDMINES().get());
			}
			if(i%SIDE==0){
				System.out.print("\n");
			}
		}

		// アイテムをセット

		// クイズをセット

		 //Massをデータベースに保存
		//Connection con = null;


			//con = Dao.getConnection();
			MassDao massdao =  new MassDao(con);

			massdao.createMass(masslist);

			}  catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		/*finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}*/
	}



	// 爆弾があるマスIDをリストで生成
	public List<Integer> minePlacement(int difficultyid,int massquantity) {
		int minequantity = 0; //爆弾数
		Difficulty df = new Difficulty(); //難易度データ
		List<Integer>randomminelist=new ArrayList<Integer>(); //爆弾があるマスIDのリスト
		List<Integer>fieldmass=new ArrayList<Integer>(); // マスIDのリスト

		try{
			//con = Dao.getConnection();
			DifficultyDao difficultydao =  new DifficultyDao(con);
			//df = difficultydao.minequantity(difficultyid);
			df = difficultydao.getDictionaryItem(difficultyid);
			// 爆弾数の取得
			minequantity=df.getMINES().get();

			// フィールドマス番号
			for(int i = 1 ; i <= massquantity ; i++){
				fieldmass.add(i);
			}

			// ランダムに順番入れ替え
			Collections.shuffle(fieldmass);

			// 地雷数に応じてマス番号をリストに保存
			for(int j = 0 ; j < minequantity ; j++){
				randomminelist.add(fieldmass.get(j));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/*finally{
			if(con!=null){
				try {
					if(con.isClosed()==false){
						con.close();
					}
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}*/


		return randomminelist;
	}


	/*public static void main(String[] args) throws ClassNotFoundException, SQLException{
		FieldService fs = new FieldService();
		fs.fieldPlacement(1, 1);
//		List<Integer>minelist = new ArrayList<Integer>();
//		minelist = fs.minePlacement(1, 256);
//		System.out.println(minelist);
	}*/
}
