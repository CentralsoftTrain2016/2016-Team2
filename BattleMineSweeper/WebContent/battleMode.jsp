<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="domain.ClickObjectTypeEnum"
    import="domain.ItemEnum" %>

<!-- author:Sasaki -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- GoogleDeveloperからライブラリを取得 -->
<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript" ></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js" type="text/javascript"></script>
<!-- JSONライブラリ -->
<script type="text/javascript" src="jquery.json.js"></script>

<script type="text/javascript" src="date.js"></script>
<script type="text/javascript" src="autoRequest.js"></script>

<script type="text/JavaScript">

		var isClickAble = true;
		var timeCountTimer;
		var isActiveTimeCount = true;
		var isStopTimer = false;
		var turnTotalTime = 0;
		var limitTimeBase = 10000;

		//var havingItemEnumName = null;

		//var doingClickAction = false;

		//初期配置
		$(document).ready( function(){
			for (var i=1 ; i<=256 ; i++){
				//動的にmass要素を生成する
				var mass = document.createElement('<%=ClickObjectTypeEnum.MASS %>');
				mass.style.position = "absolute";

				//配置する位置
				var Left = 18 * ((i-1)%16);
				var Top  = 18 * Math.floor((i-1)/16);

				//マスの位置設定
				mass.style.left = Left ;
				mass.style.top = Top ;

				//マスのID設定
				mass.id = "<%=ClickObjectTypeEnum.MASS %>" + i;

				//マスの名前設定
				mass.name = i;

				//マスの画像を読み込む
				var massImg = document.createElement('img');
				massImg.src = "masImg/mas1.png";

				//massにイメージを組み込む
				mass.appendChild(massImg);
				//ゲーム画面にマスレイヤ（mass)を組み込む
				Playfield.appendChild(mass);

			}

		});

		//JSP入ってるからJSに移せない
		function successAutoRequest(autoRequestBean){
			isMyTurn = autoRequestBean.isMyTurn;
			//alert("テスト");
			//alert(autoRequestBean.playerCharacterImageURL);
			//arart(bean.playerCharacterImageURL);

			mySkillReflesh(autoRequestBean);
			enemySkillReflesh(autoRequestBean);

			if(autoRequestBean.isMyTurn){
				document.getElementById("PlayerCharacterImg").src = autoRequestBean.playerCharacterImageURL;
				document.getElementById("EnemyCharacterImg").src =  autoRequestBean.enemyCharacterWaitImageURL;
			}else{
				document.getElementById("PlayerCharacterImg").src =  autoRequestBean.playerCharacterWaitImageURL;
				document.getElementById("EnemyCharacterImg").src =  autoRequestBean.enemyCharacterImageURL;
			}

			//クリックできないときはクリックアクションを実行中なので、画面更新をしない。
			if(isClickAble){
				for(var i=0; i< autoRequestBean.massList.length; i++){
	    	    	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + autoRequestBean.massList[i].massNumber).children[0].src = autoRequestBean.massList[i].url;
				}
			}

			//相手のアイテム状態更新
			enemyItemReflesh(autoRequestBean);

			isFinished = autoRequestBean.isFinished;
			if( isFinished ){
				//画面更新を全て無効にする
				isActiveTimeCount = false;

		    	setTimeout("goToResult()", 5000);
			}

			if( autoRequestBean.isChanged == true && isMyTurn == true ){
				alert("あなたのターン");
				timeCountInit();
			}

		}


			function timeCountInit(){
				isActiveTimeCount = true;

				//一つ前の時刻取得
				var prevTime = new Date().getTime();

				//タイムアップ時刻設定
				limitTimeBase = 10000;

				//このターンの総経過時間カウント
				turnTotalTime = 0;

				timeCount(prevTime);
			}

			function timeCount(prevTime){
					if(isActiveTimeCount){
				    	//現在時刻取得
						var currentTime = new Date().getTime();

				    	//経過時間算出
				    	var sabun = currentTime - prevTime;

				    	//総経過時間
				    	if(!isStopTimer) turnTotalTime += sabun;

				    	prevTime = currentTime;

				    	if(limitTimeBase - turnTotalTime > 0){

				    		var copy = limitTimeBase - turnTotalTime;
				    		copy = Math.floor(copy/10);

				    		for(var i=1; i<5; i++){
				    			document.getElementById("time" + i).src = "digitImg/digit" + copy%10 + ".png";
				    			copy = Math.floor(copy/10);
				    		}

				    		if(isActiveTimeCount) timeCountTimer = setTimeout("timeCount(" + prevTime + ")", 9);
				    	}else{
					    	//カウントゼロでクリック禁止
					    	isClickAble = false;
					    	//autoRequestも禁止(実行中ってことにする)
					    	doingAutoRequest = true;

			        		$.ajax({
		    	        	    type        : "POST",
		        	    	    url         : "CountZeroServlet",
		           	    	 	dataType    : "json",
		           	    	 	success     : function(data) {
		                	   		             successCountZeroFunc(data);
		                	        	    },
		             			error       : function(XMLHttpRequest, textStatus, errorThrown) {
		                	    	           	 //error(XMLHttpRequest, textStatus, errorThrown);
		                	    	        }
		            		});

				    		for(var i=1; i<5; i++){
				    			document.getElementById("time" + i).src = "digitImg/digit0.png";
				    		}
				    		alert("時間切れ\nあなたの負けです。");


			    	}
				}
			}

			function successCountZeroFunc(countZeroBean){
				for(var i=0; i<countZeroBean.massList.length; i++){
	            	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + countZeroBean.massList[i].massNumber).children[0].src = countZeroBean.massList[i].url;
				}

		    	setTimeout("goToResult()", 5000);

			}


			//各種クリックイベント
			$(function () {
				//マス
				$('<%=ClickObjectTypeEnum.MASS %>').on('click', function (){
					if( isClickAble == true && isMyTurn == true ){
						isClickAble = false;
						doMassAction(this);
					}
				});
				//アイテム
				$(document).on('click', '<%=ClickObjectTypeEnum.ITEM %>', function (){
					if( isClickAble == true && isMyTurn == true ){
						isClickAble = false;
						doItemAction(this);
					}
				});
				//スキル
				$("#PlayerSkillImg").on('click', function (){
					if( isClickAble == true && isMyTurn == true ){
						isClickAble = false;
						doSkillAction(this);
					}
				});

			});

	        function doMassAction(clickObj){
	        	//制限時間カウントストップ
	        	isStopTimer = true;

	        	//送るデータ用意
	        	var requestData = {
	            	    clickObjectType : clickObj.tagName,
	            	    clickObjectNumber : clickObj.name,
	            	    isMyTurn : isMyTurn,
	            	    turnTotalTime : turnTotalTime
	            	};

	        	//JSONに変換
	        	requestJson = $.toJSON(requestData);

	        	$.ajax({
	                type        : "POST",
	                url         : "BattleClickServlet",
	                dataType    : "json",
	                data		: {battleClickBean : requestJson},
	                success     : function(data) {
	                                successMass(data);
	                                isClickAble = true;
	                                isStopTimer = false;
	                            },
	                error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                                //error(XMLHttpRequest, textStatus, errorThrown);
	                				isClickAble = true;
	                				isStopTimer = false;
	                            }
	            });
	        }

	        function doItemAction(clickObj){
	        	//制限時間カウントストップ
	        	isStopTimer = true;

	        	//送るデータ用意
	        	var requestData = {
	            	    clickObjectType : clickObj.tagName,
	            	    isMyTurn : isMyTurn,
	            	    turnTotalTime : turnTotalTime
	            	};

	        	//JSONに変換
	        	requestJson = $.toJSON(requestData);

	        	$.ajax({
	                type        : "POST",
	                url         : "BattleClickServlet",
	                dataType    : "json",
	                data		: {battleClickBean : requestJson},
	                success     : function(data) {
	                				//10秒プラスの使用判定
	                				if( clickObj.name == "<%=ItemEnum.TIMERPURAS_ITEM %>" ){
	                					limitTimeBase += 10000;
	                				}

	                				successItem(data);
	                                isClickAble = true;
	                                isStopTimer = false;
	                            },
	                error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                                //error(XMLHttpRequest, textStatus, errorThrown);
	                				isClickAble = true;
	                				isStopTimer = false;
	                            }
	            });
	        }

	        function doSkillAction(clickObj){
	        	//制限時間カウントストップ
	        	isStopTimer = true;

	        	//送るデータ用意
	        	var requestData = {
	            	    clickObjectType : '<%=ClickObjectTypeEnum.SKILL %>',
	            	    isMyTurn : isMyTurn
	            	};

	        	//JSONに変換
	        	requestJson = $.toJSON(requestData);

	        	$.ajax({
	                type        : "POST",
	                url         : "BattleClickServlet",
	                dataType    : "json",
	                data		: {battleClickBean : requestJson},
	                success     : function(data) {
	                				successSkill(data);
	                                isClickAble = true;
	                                isStopTimer = false;
	                            },
	                error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                                //error(XMLHttpRequest, textStatus, errorThrown);
	                				isClickAble = true;
	                				isStopTimer = false;
	                            }
	            });
	        }

		     // Ajax通信成功時処理(マス)
	        function successMass(massClickResultBean) {
	            for(var i=0; i<massClickResultBean.massList.length; i++){
	            	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + massClickResultBean.massList[i].massNumber).children[0].src = massClickResultBean.massList[i].url;
		    	}
	            isMyTurn = massClickResultBean.isMyTurn;

	            //クイズビーンがあるとき
	            quizRun(massClickResultBean);
	            /*if(massClickResultBean.quizBean != null){
	    			args = new Array();
	    			args[0] = massClickResultBean.quizBean.description;

	    			var answer = window.showModalDialog('quizDialog.html', args,
	    			                       'dialogHeight:150px; dialogWidth:300px;');

	    			if(massClickResultBean.quizBean.answer != answer){
	    				//不正解処理
	    				alert("残念");
	    			}else{
	    				//正解処理
	    				alert("正解ッ・・・！");
	    			}
	            }*/

	            //アイテムビーンがあるとき
	            if(massClickResultBean.itemBean != null){
	            	//アイテム画像表示
					//動的にアイテム要素を生成する
					var item = document.createElement('<%=ClickObjectTypeEnum.ITEM %>');
					item.style.position = "absolute";

					//アイテムの位置設定
					item.style.left = 5 ;
					item.style.top = 5 ;

					//アイテムのID設定
					item.id = "My" + "<%=ClickObjectTypeEnum.ITEM %>";

					//アイテムの名前設定
					item.name = massClickResultBean.itemBean.itemEnum;

					//アイテムの画像を読み込む
					var itemImg = document.createElement('img');
					itemImg.src = massClickResultBean.myPlayerBean.itemURL;
					itemImg.style.width = 90;
					itemImg.style.height = 90;

					//itemにイメージを組み込む
					item.appendChild(itemImg);
					//ゲーム画面にアイテムレイヤ（item)を組み込む
					PlayerItemArea.appendChild(item);

	            }

	            if( isMyTurn == false ) isActiveTimeCount = false;
	            if( massClickResultBean.isFinished == true ) isActiveTimeCount = false;
	        }

		     // Ajax通信成功時処理(アイテム)
	        function successItem(itemClickResultBean) {
	            isMyTurn = itemClickResultBean.isMyTurn;

	            //アイテム消費
	            var element = document.getElementById('My<%=ClickObjectTypeEnum.ITEM %>');
	            element.parentNode.removeChild(element);

	            if( isMyTurn == false ) isActiveTimeCount = false;
	            if( itemClickResultBean.isFinished == true ) isActiveTimeCount = false;
	        }


	     	// Ajax通信成功時処理(スキル)
	        function successSkill(skillClickResultBean) {
	            isMyTurn = skillClickResultBean.isMyTurn;



	            if( isMyTurn == false ) isActiveTimeCount = false;
	            if( skillClickResultBean.isFinished == true ) isActiveTimeCount = false;
	        }

	        // Ajax通信失敗時処理
	        function error(XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + XMLHttpRequest.code + " " + XMLHttpRequest.message);
	            alert("status:" + textStatus);
	            alert("errorThrown:" + errorThrown.message);
	        }

///////////////////////////////////////////////////////////////////////////
////////////////////////      各種メソッド         ////////////////////////
///////////////////////////////////////////////////////////////////////////

	//マス更新
	function massReflesh(massClickResultBean){
		if(massClickResultBean.massList != null){
			for(var i=0; i<massClickResultBean.massList.length; i++){
				document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + massClickResultBean.massList[i].massNumber).children[0].src = massClickResultBean.massList[i].url;
			}
		}
	}

	//手番更新
	function turnReflesh(massClickResultBean){
		if(massClickResultBean.isMyTurn != null){
			isMyTurn = massClickResultBean.isMyTurn;
		}
	}

	//クイズ実行
	function quizRun(massClickResultBean){
		if(massClickResultBean.quizBean != null){
			args = new Array();
			args[0] = massClickResultBean.quizBean.description;

			var answer = window.showModalDialog('quizDialog.html', args,
			                       'dialogHeight:150px; dialogWidth:300px;');

			if(massClickResultBean.quizBean.answer != answer){
				//不正解処理
				$.ajax({
	                type        : "POST",
	                url         : "QuizIncorrectServlet",
	                dataType    : "json",
	                success     : function(data) {
				    				alert("残念");
	                            },
	                error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                                //error(XMLHttpRequest, textStatus, errorThrown);
	                            }
	            });
			}else{
				//正解処理
				alert("正解ッ・・・！");
			}
		}
	}

	//自分のアイテム取得
	function myItemGet(massClickResultBean){
		//自分のアイテム
        if(massClickResultBean.itemBean != null){
        	//アイテム画像表示
			//動的にアイテム要素を生成する
			var item = document.createElement('<%=ClickObjectTypeEnum.ITEM %>');
			item.style.position = "absolute";

			//アイテムの位置設定
			item.style.left = 5 ;
			item.style.top = 5 ;

			//アイテムのID設定
			item.id = "My" + "<%=ClickObjectTypeEnum.ITEM %>";

			//アイテムの名前設定
			item.name = massClickResultBean.itemBean.itemEnum;

			//アイテムの画像を読み込む
			var itemImg = document.createElement('img');
			itemImg.src = massClickResultBean.myPlayerBean.itemURL;
			itemImg.style.width = 90;
			itemImg.style.height = 90;

			//itemにイメージを組み込む
			item.appendChild(itemImg);
			//ゲーム画面にアイテムレイヤ（item)を組み込む
			PlayerItemArea.appendChild(item);
        }
	}

	//相手のアイテム更新
	function enemyItemReflesh(autoRequestBean){
		//相手のアイテム
        if(autoRequestBean.enemyPlayerBean != null){
        	document.getElementById("EnemyItemImg").src = autoRequestBean.enemyPlayerBean.itemURL;
        }
   	}

	//自分のスキル更新
	function mySkillReflesh(autoRequestBean){
        if(autoRequestBean.myPlayerBean != null){
        	document.getElementById("PlayerSkillImg").src = autoRequestBean.myPlayerBean.skillURL;

        	var coolTime = autoRequestBean.myPlayerBean.coolTime;
        	if(coolTime < 0){
        		document.getElementById("PlayerCoolTime1").src = "";
        		document.getElementById("PlayerCoolTime2").src = "";
        	}

        	var coolTime_1 = coolTime%10;
        	var coolTime_10 = Math.floor(coolTime/10) % 10;

        	if(coolTime_10 != 0){
        		document.getElementById("PlayerCoolTime1").src = "digitImg/digit" + coolTime_10 + ".png";
        	}else{
        		document.getElementById("PlayerCoolTime1").src = "";
        	}

        	if(coolTime_1 != 0 || coolTime_10 != 0){
        		document.getElementById("PlayerCoolTime2").src = "digitImg/digit" + coolTime_1 + ".png";
        	}else{
        		document.getElementById("PlayerCoolTime2").src = "";
        	}

        }
	}

	//相手のスキル更新
	function enemySkillReflesh(autoRequestBean){
        if(autoRequestBean.enemyPlayerBean != null){
        	document.getElementById("EnemySkillImg").src = autoRequestBean.enemyPlayerBean.skillURL;
        }

    	var coolTime = autoRequestBean.enemyPlayerBean.coolTime;
    	if(coolTime < 0){
    		document.getElementById("EnemyCoolTime1").src = "";
    		document.getElementById("EnemyCoolTime2").src = "";
    	}

    	var coolTime_1 = coolTime%10;
    	var coolTime_10 = Math.floor(coolTime/10) % 10;

    	if(coolTime_10 != 0){
    		document.getElementById("EnemyCoolTime1").src = "digitImg/digit" + coolTime_10 + ".png";
    	}else{
    		document.getElementById("EnemyCoolTime1").src = "";
    	}

    	if(coolTime_1 != 0 || coolTime_10 != 0){
    		document.getElementById("EnemyCoolTime2").src = "digitImg/digit" + coolTime_1 + ".png";
    	}else{
    		document.getElementById("EnemyCoolTime2").src = "";
    	}

	}

////////////////////////////////////////////////////////////////////////


</script>

</head>
<body onLoad="startAutoRequest();">

<form>
<div id="Gamefield"
     		style="position:absolute; left:10px; top:10px;
                width: 640px; height: 480px; border-style: none; background-color: #000000;">
    <img id="BackGround" src="bgImg/BackGround3.jpg"
		style="position:absolute; left:0px; top:0px;
	            width: 640px; height: 480px; border-style: none;">
    <img id="BackGroundFrame" src="bgImg/BackGroundFrame.png"
		style="position:absolute; left:0px; top:0px;
	            width: 640px; height: 480px; border-style: none;">

    <div id="Timer"
     		style="position:absolute; left:176px; top:10px;
                width: 288px; height: 50px; border-style: none; background: none;">
		<img id="TimerFrame" src="bgImg/TimerFrame.png"
			style="position:absolute; left:70px; top:0px;
	                width: 150px; height: 50px; border-style: none;">
		<img id="time4" src="digitImg/digit0.png"
			style="position:absolute; left:90px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time3" src="digitImg/digit0.png"
			style="position:absolute; left:115px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time2" src="digitImg/digit0.png"
			style="position:absolute; left:148px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time1" src="digitImg/digit0.png"
			style="position:absolute; left:173px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
	   	<img id="dot" src="digitImg/dot.png"
			style="position:absolute; left:140px; top:5px;
	                width: 8px; height: 40px; border-style: none;">

    </div>

   	<div id="PlayfieldFrameArea"
     		style="position:absolute; left:168px; top:62px;
                width: 304px; height: 296px; border-style: none; background: none;">
    	<img id="PlayfieldFrame" src="bgImg/PlayFieldFrame.jpg"
			style="position:absolute; left:0px; top:0px;
	            width: 304px; height: 296px; border-style: none;">
    </div>


   	<div id="Playfield"
     		style="position:absolute; left:176px; top:66px;
                width: 288px; height: 288px; border-style: none; background: none;">


	</div>

   	<div id="PlayerCharacterFrameArea"
     		style="position:absolute; left:5px; top:7px;
                width: 166px; height: 466px; border-style: none; background: none;">
    	<img id="PlayerCharacterFrame" src="bgImg/PlayFieldFrame.jpg"
			style="position:absolute; left:0px; top:0px;
	            width: 166px; height: 466px; border-style: none;">
    </div>


	<div id="PlayerCharacter"
     		style="position:absolute; left:8px; top:10px;
                width: 160px; height: 460px; border-style: none; background: none;">
        <div id="PlayerCharacterImgArea"
     		style="position:absolute; left:0px; top:0px;
                width: 160px; height: 320px; border-style: none; background: none;">
	   		<img id="PlayerCharacterImg" src=""
				style="position:absolute; left:0px; top:0px;
	                width: 160px; height: 320px; border-style: none;">

	</div>

        <div id="PlayerCharacterTotalTime"
     		style="position:absolute; left:10px; top:320px;
                width: 140px; height: 30px; border-style: none; background: none;">
        </div>

        <div id="PlayerCharacterSkill"
     		style="position:absolute; left:35px; top:360px;
                width: 90px; height: 90px; border-style: none; background: none;">
                <img id="PlayerSkillImg" src=""
				style="position:absolute; left:0px; top:0px;
	                width: 90px; height: 90px; border-style: none;">


                <div id="PlayerCharacterSkillCoolTime"
     				style="position:absolute; left:55px; top:55px;
                		width: 30px; height: 30px; border-style: none; background: none;">
                	<img id="PlayerCoolTime1" src=""
							style="position:absolute; left:0px; top:6px;
	                		width: 15px; height: 24px; border-style: none;">
                	<img id="PlayerCoolTime2" src=""
							style="position:absolute; left:15px; top:6px;
	                		width: 15px; height: 24px; border-style: none;">
               	</div>
        </div>

	</div>

   	<div id="EnemyCharacterFrameArea"
     		style="position:absolute; left:469px; top:7px;
                width: 166px; height: 466px; border-style: none; background: none;">
    	<img id="EnemyCharacterFrame" src="bgImg/PlayFieldFrame.jpg"
			style="position:absolute; left:0px; top:0px;
	            width: 166px; height: 466px; border-style: none;">
    </div>

	<div id="EnemyCharacter"
     		style="position:absolute; left:472px; top:10px;
                width: 160px; height: 460px; border-style: none; background: none;">
        <div id="EnemyCharacterImgArea"
     		style="position:absolute; left:0px; top:0px;
                width: 160px; height: 320px; border-style: none; background: none;">
            <img id="EnemyCharacterImg" src=""
				style="position:absolute; left:0px; top:0px;
	                width: 160px; height: 320px; border-style: none;">

	    </div>

        <div id="EnemyCharacterTotalTime"
     		style="position:absolute; left:10px; top:320px;
                width: 140px; height: 30px; border-style: none; background: none;">
        </div>

        <div id="EnemyCharacterSkill"
     		style="position:absolute; left:35px; top:360px;
                width: 90px; height: 90px; border-style: none; background: none;">
                <img id="EnemySkillImg" src=""
				style="position:absolute; left:0px; top:0px;
	                width: 90px; height: 90px; border-style: none;">


                <div id="EnemyCharacterSkillCoolTime"
     				style="position:absolute; left:55px; top:55px;
                		width: 30px; height: 30px; border-style: none; background: none;">
                	<img id="EnemyCoolTime1" src=""
							style="position:absolute; left:0px; top:6px;
	                		width: 15px; height: 24px; border-style: none;">
                	<img id="EnemyCoolTime2" src=""
							style="position:absolute; left:15px; top:6px;
	                		width: 15px; height: 24px; border-style: none;">
               	</div>
        </div>

	</div>

	<div id="PlayerItemFrame"
     		style="position:absolute; left:176px; top:362px;
                width: 140px; height: 108px; border-style: none; background: none;">
		<img id="PlayerItemWindow" src="bgImg/ItemWindow.png"
			style="position:absolute; left:20px; top:4px;
	            width: 100px; height: 100px; border-style: none;">
        <div id="PlayerItemArea"
        	style="position:absolute; left:20px; top:4px;
	            width: 100px; height: 100px; border-style: none;">
	    </div>
	</div>

	<div id="EnemyItemFrame"
     		style="position:absolute; left:324px; top:362px;
                width: 140px; height: 108px; border-style: none; background: none;">
		<img id="EnemyItemWindow" src="bgImg/ItemWindow.png"
			style="position:absolute; left:20px; top:4px;
	            width: 100px; height: 100px; border-style: none;">
        <div id="EnemyItemArea"
        	style="position:absolute; left:20px; top:4px;
	            width: 100px; height: 100px; border-style: none;">
	    	<img id="EnemyItemImg" src=""
				style="position:absolute; left:5px; top:5px;
	            	width: 90px; height: 90px; border-style: none;">
	    </div>

	</div>

</div>
</form>
</body>
</html>