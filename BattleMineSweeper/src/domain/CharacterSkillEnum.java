package domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.GameDao;
import dao.ItemDao;
import dao.PlayerDao;
import dao.QuizDao;
import domain.value.COOLTIME;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.ITEMID;
import domain.value.PLAYERID;
import service.ChangeTurnPlayerService;
import service.bean.ClickResultBean;

public enum CharacterSkillEnum {
		SAMON_SKILL("欲求のイケバタ",1,"charImg/samon_action.png","charImg/samon_wait.png","skillImg/samon_skill.jpg")
		{

			@Override
			/**1/3の確率でアイテム取得or2/3の確率でクイズ発生
			 *
			 */
			public ClickResultBean useSkill(GAMEID gameID, ClickPlayerID playerID, Connection con)
					throws SQLException, ClassNotFoundException {
						ClickResultBean bean = new ClickResultBean();
						//1/3でアイテム取得、そうでないときクイズ発生
						if( Math.random() < 1.0/3.0){
							System.out.println("Get Item!");
							//1<=x<4を切り捨ててintにキャスト
							int itemID = (int) Math.floor(Math.random() * 3 +1);
							//itemを持っていないとき、アイテムを取得
							PlayerDao pdao = new PlayerDao(con);
							Player myself = pdao.getPlayer(playerID.get());
							if( myself.getITEMID().get() == 0){
								//Playerのアイテム情報を更新
								pdao.setItem(playerID,itemID);
								Item item = new ItemDao(con).getItem(itemID);
								bean.setItem(item);
							}else{
								System.out.println("Uh...Already having an Item.");
							}
						}
						else{
							System.out.println("Quiz Start!");
							//クイズが何種類あるかはどうやってとってこよう？（暫定11種）
							//1<=x<quizQuantityを切り捨ててintにキャスト
							//List<Quiz> qList = new QuizDao(con).
							int quizID = (int) Math.floor(Math.random() * quizQuantity +1 );
							Quiz quiz = new QuizDao(con).getDictionaryItem(quizID);
							bean.setQuiz(quiz);
						}

						return bean;

			}

			@Override
			public void setCoolTime(ClickPlayerID playerID, Connection con) throws SQLException, ClassNotFoundException {
				COOLTIME coolTime = new COOLTIME(6);
				PlayerDao pdao = new PlayerDao(con);
				pdao.setCoolTime(playerID, coolTime);
			}

			@Override
			public COOLTIME getCoolTime() {
				// TODO 自動生成されたメソッド・スタブ
				return new COOLTIME(6);
			}


		},
		SASAKYU_SKILL("ねむりのササキ",2,"charImg/sasaki_action.png","charImg/sasaki_wait.png","skillImg/sasakyu_skill.jpg")
		{
			@Override
			/**スキル効果：１手パス
			 *
			 */
			public ClickResultBean useSkill(GAMEID gameID, ClickPlayerID playerID, Connection con)
					throws SQLException, ClassNotFoundException {
				ClickResultBean bean = new ClickResultBean();
				//GAMEからturnPlayerを取得
				GameDao gdao = new GameDao(con);
				Game game = gdao.getGame(gameID.get());
				//turnPlayerと自分のIDを比較し、同じなら相手のIDに変える(パス)
				if( playerID.get() == game.getTURNPLAYERID().get() ){
					ChangeTurnPlayerService ctps = new ChangeTurnPlayerService(con);
					ctps.changeTurnPlayer(gameID);
				}

				return bean;
			}

			@Override
			public void setCoolTime(ClickPlayerID playerID, Connection con) throws SQLException, ClassNotFoundException {
				COOLTIME coolTime = new COOLTIME(12);
				PlayerDao pdao = new PlayerDao(con);
				pdao.setCoolTime(playerID, coolTime);
			}

			@Override
			public COOLTIME getCoolTime() {
				// TODO 自動生成されたメソッド・スタブ
				return new COOLTIME(12);
			}

		},
		KASANO_SKILL("DBのトキオ",3,"charImg/kasano_action.gif","charImg/kasano_wait.png","skillImg/kasano_skill.jpg")
		{
			@Override
			/**スキル効果：アイテム強奪
			 *
			 */
			public ClickResultBean useSkill(GAMEID gameID, ClickPlayerID playerID, Connection con)
					throws SQLException, ClassNotFoundException {
				ClickResultBean bean = new ClickResultBean();
				GameDao gdao = new GameDao(con);
				Game gm = gdao.getGame(gameID.get());
				//ゲームIDから相手プレイヤの情報を取得
				PLAYERID enemyPlayerID = null;
				if( playerID.get() == gm.getPLAYER1ID().get() ){
					enemyPlayerID = gm.getPLAYER2ID();
				}
				else{
					enemyPlayerID = gm.getPLAYER1ID();
				}
				PlayerDao pdao = new PlayerDao(con);
				Player enemyPlayer = pdao.getPlayer(enemyPlayerID.get());
				//相手のアイテムを取り上げる
				ITEMID itemID = enemyPlayer.getITEMID();
				pdao.consumeItem(enemyPlayer.getPLAYERID());
				enemyPlayer = pdao.getPlayer(enemyPlayerID.get());
				bean.setEnemyPlayer(enemyPlayer);

				//相手がアイテムを持っていて、且つ自分がアイテムを持っていなかった場合にアイテムを強奪
				Player myself = pdao.getPlayer(playerID.get());
				if( itemID.get() != 0 &&  myself.getITEMID().get() == 0){
					Item item = new ItemDao(con).getItem(itemID.get());
					pdao.setItem(myself.getPLAYERID(), itemID.get());
					bean.setMyPlayer(pdao.getPlayer(playerID.get()));
					bean.setItem(item);
				}
				return bean;
			}

			@Override
			public void setCoolTime(ClickPlayerID playerID, Connection con) throws SQLException, ClassNotFoundException {
				COOLTIME coolTime = new COOLTIME(10);
				PlayerDao pdao = new PlayerDao(con);
				pdao.setCoolTime(playerID, coolTime);

			}

			@Override
			public COOLTIME getCoolTime() {
				// TODO 自動生成されたメソッド・スタブ
				return new COOLTIME(10);
			}
		};

		//CharacterSkillEnumのなかのいろいろ
		private String skillName;
		private int characterID;
		private static Map< Integer , CharacterSkillEnum> map = new HashMap<Integer, CharacterSkillEnum>();
		private String imageURL;
		private String waitImageURL;
		private String skillURL;

		private static final int quizQuantity = 11;


		private CharacterSkillEnum(String skillName, int characterID, String imageURL, String waitImageURL,String skillURL) {
			this.skillName = skillName;
			this.characterID = characterID;
			this.imageURL = imageURL;
			this.waitImageURL = waitImageURL;
			this.skillURL = skillURL;
		}
		static{
			map.put(1, SAMON_SKILL);
			map.put(2, SASAKYU_SKILL);
			map.put(3, KASANO_SKILL);
		}

		public String getSkillName() {
			return this.skillName;
		}
		public int getCharacterID(){
			return this.characterID;
		}

		public String getImageURL() {
			return imageURL;
		}
		public String getWaitImageURL() {
			return waitImageURL;
		}
		public String getskillURL() {
			return skillURL;
		}
		public static CharacterSkillEnum getSkillEnumByValue(int value){
			return map.get(value);
		}

		//作るべきメソッドを抽象メソッドで定義しておく
		public abstract ClickResultBean useSkill(GAMEID gameID, ClickPlayerID playerID, Connection con) throws SQLException,ClassNotFoundException ;
		public abstract void setCoolTime(ClickPlayerID playerID, Connection con) throws SQLException,ClassNotFoundException ;
		public abstract COOLTIME getCoolTime();

}
