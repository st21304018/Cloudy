/**
 *
 */
function hideForm() {
	document.getElementById("box").style.display = "none";
	var target = document.getElementById("thread");
	target.style.backgroundColor = "rgba(255, 255, 255)";
	var top = document.getElementById("top");
	top.style.pointerEvents = "auto";
	var title = document.getElementById("title");
	title.style.backgroundColor = "rgba(255, 255, 255, 0.95)";
}
function showForm() {
	document.getElementById("box").style.display = "block";
	var target = document.getElementById("thread");
	target.style.backgroundColor = "rgba(0, 0, 0, 0.4)";
	var top = document.getElementById("top");
	top.style.pointerEvents = "none";
	var title = document.getElementById("title");
	title.style.backgroundColor = "rgba(0, 0, 0, 0.02)";
}

function logoutCheck(){
	var result = confirm('ログアウトしてよろしいですか？');

	if(result){
		window.location.href = 'logout';
	}
}
$(function () {
	$("#input_comment").on('keydown keyup keypress change', function () {
		let count = $(this).val().length;
		let limit = 200 - count;
		$("#num").text(count);
		$("input[type='submit']").prop('disabled', false).removeClass('disabled');
		$("input[type='submit']").removeClass('error-submit');
		$("#num").css('color', 'rgba(0, 0, 0, 0.5)');
		if (limit <= 0) {
			$("#num").text(count);
			$("#num").css('color', '#ff0000');
			$("input[type='submit']").prop('disabled', true).addClass('disabled');
			$("input[type='submit']").addClass('error-submit');
     	}
	});
});