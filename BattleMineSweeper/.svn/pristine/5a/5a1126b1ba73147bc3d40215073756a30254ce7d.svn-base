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

<script type="text/JavaScript">

		var isClickAble = true;
		var isMyTurn = true;
		var isFinished = false;

		//READY.GOのやつ

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

			$(function () {
				$('<%=ClickObjectTypeEnum.MASS %>').on('click', function (){
					if( isClickAble==true ){
						//クリック処理が終わるまでクリック禁止
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

	            isFinished = massClickResultBean.isFinished;
	            if(isFinished){
	    		   	setTimeout("goToResult()", 5000);
	            }
	        }

	        // Ajax通信失敗時処理
	        function error(XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + XMLHttpRequest);
	            alert("status:" + textStatus);
	            alert("errorThrown:" + errorThrown);
	        }

	    	function goToResult(){
	    		location.href="BattleResultServlet";
	    	}



	        //経過時間カウント
			$(document).ready( function(){
				//タイムカウンター初期化
				allTimeCountInit();

				});

			function allTimeCountInit(){
				//開始時刻取得
				var startTime = new Date().getTime();

				allTimeCount(startTime);
			}

			function allTimeCount(startTime){
			    //現在時刻取得
				var currentTime = new Date().getTime();

			    var sabun = currentTime - startTime;

			    //秒にする
		    	sabun = Math.floor(sabun/1000);

		    	var seconds = sabun%60;

		    	//分にする
		    	sabun = Math.floor(sabun/60);

		    	var minutes = sabun%60;

		    	//秒描画
		    	for(var i=1; i<3; i++){
		    		document.getElementById("time" + i).src = "digitImg/digit" + seconds%10 + ".png";
		    		seconds = Math.floor(seconds/10);
		    	}

		    	//分描画
		    	for(var i=3; i<5; i++){
		    		document.getElementById("time" + i).src = "digitImg/digit" + minutes%10 + ".png";
		    		minutes = Math.floor(minutes/10);
		    	}

		    	setTimeout("allTimeCount(" + startTime + ")", 9);
			}



</script>

</head>
<body>

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
     		style="position:absolute; left:176px; top:30px;
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
	   	<img id="koron" src="digitImg/koron.png"
			style="position:absolute; left:140px; top:5px;
	                width: 8px; height: 40px; border-style: none;">
    </div>

    <div id="PlayfieldFrameArea"
     	style="position:absolute; left:168px; top:102px;
            width: 304px; height: 296px; border-style: none; background: none;">
    	<img id="PlayfieldFrame" src="bgImg/PlayFieldFrame.jpg"
			style="position:absolute; left:0px; top:0px;
	            width: 304px; height: 296px; border-style: none;">
    </div>

   	<div id="Playfield"
     		style="position:absolute; left:176px; top:106px;
                width: 288px; height: 288px; border-style: none; background-color: #000000;">
	</div>
</div>
</form>
</body>
</html>