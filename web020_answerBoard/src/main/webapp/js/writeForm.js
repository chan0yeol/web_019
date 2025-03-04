// 화면을 강제로 이동할 때 입력되어 있는 글이 저장이 되지 않는다는 경고, ex)양식이 저장되지 않습니다. 등
let isShow = true;

window.onbeforeunload = function () {
	if(isShow){
	return "화면에서 벗어납니다. 작성된 글은 저장되지 않습니다.";
	}
}

// 새로고침과 같은 화면 REFRESH의 이벤트를 막음
// F5:116, r:82 , n:78, ctrl:17
var isCtrl = false; // ctrl키의 조합키 판단을 위한 값
document.addEventListener("keydown", (e)=>{
	if(e.keyCode === 17) {
		isCtrl = true;	
	}
 // 				 F5					r							n
	if((e.keyCode===116)||(e.keyCode===82 && isCtrl)||(e.keyCode===78 && isCtrl)) {
		console.log(e.keyCode === 116?"F5":e.keyCode===82?"ctrl+r":"ctrl+n");
		e.preventDefault();
		e.stopPropagation();
		return false;
	}
});
document.addEventListener("keyup", (e)=>{
	if(e.keyCode===17){
	isCtrl = false;
	}
});
// 화면의 값 유효성 검사
function validateForm() {
	console.log("form 요소의 유효성 검사");
	var frm = document.forms[0];
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	
	if(title.value == "" || content == "") {
		Swal.fire("필수값을 입력해주세요");
	} else{
		// XSS(Cross Side Script) 방지 textarea 생성 -> HTML 출력
		var str = content.value;
		str = str.replace(/</gim,"&lt;");
		str = str.replace(/>/gim,"&gt;");
		str = str.replace(/\'/gim,"&apos;");
		str = str.replace(/\r\n|\r|\n|\n\r/gim,"<br>");
		document.getElementById("contentView").innerHTML = str;
		content.value=str;
		isShow = false;
		frm.submit();
		
	}
}