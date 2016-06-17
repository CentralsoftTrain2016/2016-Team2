<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="domain.ClickObjectTypeEnum" %>

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
<script type="text/javascript" src="jquery.periodicalupdater.js"></script>

<script type="text/javascript" src="date.js"></script>
<script type="text/javascript" src="autoRequest.js"></script>

<script type="text/JavaScript">

		var isClickAble = true;
		var timeCountTimer;
		var isActiveTimeCount = true;

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
			if(autoRequestBean.isMyTurn){
				document.getElementById("PlayerCharacter").style.backgroundColor = '#ffff00';
				document.getElementById("EnemyCharacter").style.backgroundColor = '#999900';
			}else{
				document.getElementById("PlayerCharacter").style.backgroundColor = '#999900';
				document.getElementById("EnemyCharacter").style.backgroundColor = '#ffff00';
			}

			for(var i=0; i<autoRequestBean.massList.length; i++){
	        	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + autoRequestBean.massList[i].massNumber).children[0].src = autoRequestBean.massList[i].url;
			}

			isFinished = autoRequestBean.isFinished;
			if( isFinished ){
				//画面更新を全て無効にする
				isActiveTimeCount = false;

		    	setTimeout("goToResult()", 5000);
			}

			if( autoRequestBean.isChanged == true ){
				//timeCountInit();
			}

		}


		$(document).ready( function(){
			//alert(comDateParse('2016/06/15 10:41:30.987','yyyy/MM/dd HH:mm:ss.fff').getMilliseconds());

			});

			/*function timeCountInit(){
				//現在時刻取得
				var currentTime = new Date().getTime();

				//タイムアップ時刻設定
				var endTime = currentTime + 10000;

				timeCount(endTime);
			}

			function timeCount(endTime){
			    //現在時刻取得
				var currentTime = new Date().getTime();

			    var sabun = endTime - currentTime;

			    if(sabun > 0){

			    	sabun = Math.floor(sabun/10);

			    	for(var i=1; i<5; i++){
			    		document.getElementById("time" + i).src = "digitImg/digit" + sabun%10 + ".gif";
			    		sabun = Math.floor(sabun/10);
			    	}

			    	if(isActiveTimeCount) timeCountTimer = setTimeout("timeCount(" + endTime + ")", 9);
			    }else{
			    	for(var i=1; i<5; i++){
			    		document.getElementById("time" + i).src = "digitImg/digit0.gif";
			    	}

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

			    }
			}

			function successCountZeroFunc(countZeroBean){
				for(var i=0; i<countZeroBean.massList.length; i++){
	            	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + countZeroBean.massList[i].massNumber).children[0].src = countZeroBean.massList[i].url;
				}

			}*/




			$(function () {
				$('<%=ClickObjectTypeEnum.MASS %>').on('click', function (){
					//とりあえず開いたような表示
					//this.children[0].src = "masImg/masType8.png";
					//alert(this.tagName);
					if( isClickAble==true ){
						isClickAble = false;
						doMassAction(this);
					}
				});
			});

	        function doMassAction(clickObj){
	        	//送るデータ用意
	        	var requestData = {
	            	    clickObjectType : clickObj.tagName,
	            	    clickObjectNumber : clickObj.name,
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
	                                successMass(data);
	                                isClickAble = true;
	                            },
	                error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                                //error(XMLHttpRequest, textStatus, errorThrown);
	                				isClickAble = true;
	                            }
	            });
	        }

	     // Ajax通信成功時処理
	        function successMass(massClickResultBean) {
	            for(var i=0; i<massClickResultBean.massList.length; i++){
	            	document.getElementById("<%=ClickObjectTypeEnum.MASS %>" + massClickResultBean.massList[i].massNumber).children[0].src = massClickResultBean.massList[i].url;
		    	}
	            isMyTurn = massClickResultBean.isMyTurn;
	            if( isMyTurn == false ) isActiveTimeCount = false;
	        }

	        // Ajax通信失敗時処理
	        function error(XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + XMLHttpRequest);
	            alert("status:" + textStatus);
	            alert("errorThrown:" + errorThrown);
	        }


</script>

</head>
<body>

<form>
<div id="Gamefield"
     		style="position:absolute; left:10px; top:10px;
                width: 640px; height: 480px; border-style: none; background-color: #000000;">
    <div id="Timer"
     		style="position:absolute; left:176px; top:10px;
                width: 288px; height: 50px; border-style: none; background-color: #fff;">
                残り時間
		<img id="time4" src="digitImg/digit0.gif"
			style="position:absolute; left:90px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time3" src="digitImg/digit0.gif"
			style="position:absolute; left:115px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time2" src="digitImg/digit0.gif"
			style="position:absolute; left:148px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
		<img id="time1" src="digitImg/digit0.gif"
			style="position:absolute; left:173px; top:5px;
	                width: 25px; height: 40px; border-style: none;">
    </div>
   	<div id="Playfield"
     		style="position:absolute; left:176px; top:66px;
                width: 288px; height: 288px; border-style: none; background-color: #000000;">
	</div>
	<div id="PlayerCharacter"
     		style="position:absolute; left:8px; top:10px;
                width: 160px; height: 460px; border-style: none; background-color: #ffff00; color: #fff">
                自分キャラ
	</div>
	<div id="EnemyCharacter"
     		style="position:absolute; left:472px; top:10px;
                width: 160px; height: 460px; border-style: none; background-color: #999900; color: #fff">
                相手キャラ
	</div>
	<div id="PlayerItem"
     		style="position:absolute; left:176px; top:362px;
                width: 140px; height: 108px; border-style: none; background-color: #00ff00; color: #fff">
                自分アイテム
	</div>
	<div id="EnemyItem"
     		style="position:absolute; left:324px; top:362px;
                width: 140px; height: 108px; border-style: none; background-color: #009900; color: #fff">
                相手アイテム
	</div>
</div>
</form>
</body>
</html>