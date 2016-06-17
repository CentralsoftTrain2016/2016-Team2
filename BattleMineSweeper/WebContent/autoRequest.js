//battle.jspのオートリクエスト機能

	var isMyTurn = false;
	var isFinished = false;

	$(document).ready( function(){
		var autoRequestTimer = setTimeout("autoRequestFunc()",3000);
		});

	function autoRequestFunc(){
    	$.ajax({
            type        : "POST",
            url         : "AutoRequestServlet",
            dataType    : "json",
            data		: {isMyTurn : isMyTurn},
            success     : function(data) {
            				if( !isFinished ) successAutoRequest(data);
                        },
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            //error(XMLHttpRequest, textStatus, errorThrown);
                        }
        });

		if( !isFinished ) autoRequestTimer = setTimeout("autoRequestFunc()",3000);
	}


	function goToResult(){
		location.href="BattleResultServlet";
;
	}
