$(function () {
	$('#Dialog-Box').dialog({
       autoOpen: false,
       height: 300,
       width: 500,
       modal: false,
	});
	document.getElementById("Dialog-Box").style.visibility='visible';
});

function showWordDetails(word) {
	openDialogBox(word);
}

function openDialogBox(word) {
	$('#Dialog-Box').dialog('option', 'title', 'Informacje o słowie: \''+word+'\'');
	
	echoControllerCall(word);
	
	document.getElementById("Dialog-Box_pos").innerText = "<placeholder>";
	document.getElementById("Dialog-Box_definition").innerText = "<placeholder>";
	document.getElementById("Dialog-Box_examples").innerText = "<placeholder>";
    $('#Dialog-Box').dialog('open');
}

function echoControllerCall(word) {
	$.ajax({
        url: '/echo',
        dataType:'json',
        type: 'get',
        data: JSON.stringify({ 'message': word }),
        success: function(response){
              console.log("Success, response: " + response);
        }
    });
}