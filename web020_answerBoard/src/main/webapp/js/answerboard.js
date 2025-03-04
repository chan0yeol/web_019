/*
  - onload 는 HTML이 모두 로드된 후에 마지막에 실행되는 함수.
  - Jquery : $(function(){});, $(document).ready(function(){});
  
  javascript : window.onload(function(){}); , 
  		document.addEventListener("DomContentLoaded", function() {})
*/

// GNB에 있는 목록을 클릭했을 경우  Bootstrap의 class='active'가 적용되도록 작성.

// jquery 방식
//$(document).ready(function () {
//	console.log("Jquery onload 실행");
//	console.log($("ul.navbar-nav>li"));
//	$(".navbar-nav>li").click(() => {
//		//현재 클릭된 li의 class 추가 > 같은 레벨에 있는 tag는 class삭제 후 
//		console.log(this);
//		$(this).addClass("active").siblings().removeClass("actvie");
//		$(this).parent().siblings().find("li").removeClass("active");		
//	})
//});

// javascript 방식
document.addEventListener("DOMContentLoaded", function () {
	console.log("addEventListener 실행");
	var lis = document.querySelectorAll(".navbar-nav>li");
	console.log(lis);
	lis.forEach(function(item) {
		item.addEventListener("click", function(){
			// 현재 클릭한 li의 class=active
//			this.addClass("active");
			this.classList.add("active");
			// 현재 클릭한 li를 제외한 다른 li들의 class=active 제거
			lis.forEach(function (other){
				if(other != item) {
					other.classList.remove("active");
				}
			})
		})
	})	
});

// boardList.jsp의 기능
// 1. id='chkbox' 혹은 class='allCheckBox' 를 클릭했을때 
// name='ch' 혹은 class='ch' 인 checkbox를 checked로
// Jquery 
/*$(function(){
	console.log("Jquery allCheckBox() 실행");
	$(".allCheckBox").click(function() {
		$(".ch").prop("checked",this.checked);
	});
});
*/
// js
function checkAll(bool) {
	console.log("js onclick을 통한 이벤트");
	var chs = document.getElementsByName("ch");
	for(let i=0; i<chs.length;i++) {
		chs[i].checked = bool;
	}
}

// 2.  name='ch' 혹은 class='ch' 인 checkbox가 체크된 상태에 따라
// 모두 체크되엇다면 id="chk" 혹은 class="allCheckBox" 체크
// 아니라면 체크 해제

// Jquery
//$(document).ready(function(){
//	console.log("ch에 따른 전체선택 해제 및 전체선택");		
//	var chs = $("input[name=ch]");
//	var allChk = $("#chkbox");
//	chs.click(function (){
//		// var chsCheckLen = $("input[name=ch:checked]").length 와 아래의 filter 같은거 
//		allChk.prop("checked", chs.filter(":checked").length == chs.length)
//	});
//});

// javascript 
var chCheckedCount = () => {
	var chs = document.getElementsByName("ch");
	let cnt = 0;
	for(let i=0;i<chs.length;i++){
		if(chs[i].checked){
			cnt++;
		}
	}
	return cnt;
}

window.onload = function () {
	console.log("js ch에 따른 allCheckBox 변경 온로드 실행");
	
	var chs = document.getElementsByName("ch");
	var chkbox = document.getElementById("chkbox");
	for(let i=0;i<chs.length;i++){
		chs[i].onclick = () => {
			if(chs.length === chCheckedCount()){
				chkbox.checked = true;
			} else{
				chkbox.checked = false;
			}
		}
	}
}


// submit의 프로파게이션(Propagation) 처리 방법
// submit 이벤트는 form에서 동작이 되는 이벤트 form에 선언되어 있는 action의 서버로 데이터를 x-www-form-urlencoded
// Propagation은 전파(html의 속성(a 태그의 href, location.href, on*** 이벤트)를 막는다
//  -> event.preventDefault(), evnet.stopPropagation();
//  -> return false; 

// 1. html의 form의 onsubmit을 통한 propagation을 처리하고 javascript에서 submit을 처리한다.
function chkSubmit() {
	console.log("form 의 event인 submit 실행");
	var chCount = chCheckedCount();
	if(chCount == 0) {
		alert("반드시 1개 이상의 글을 선택해주세요");
	} else{
		submitForm();
	}
	return false;
}

function submitForm() {
	document.forms[0].submit();
}

// 2. javascript의 라이브러리인 sweetalert2 을통한 callback 작업후 submit 처리
function chSubmit(event) {
	console.log("다중삭제02 의 event : ",event);
	event.preventDefault();
	
	var cnt = chCheckedCount();
	if(cnt>0){
		Swal.fire({
			  title: "다중삭제를 진행 합니까?",
			  icon:"question",
			  showDenyButton: true,
//			  showCancelButton: true, // callback 작동 안하고 바로 종료
			  showCloseButton: true,
			  confirmButtonText: "삭제",
			  denyButtonText: `취소`
			}).then((result) => {
			  /* TRUE 라면 isConfirmed False라면 isDenied */
			  if (result.isConfirmed) {
			    Swal.fire("Saved!", "", "success").then(()=> {
			    	submitForm();	
				});
			  } else if (result.isDenied) {
			    Swal.fire("Changes are not saved", "", "info");
			  }
		});
	} else{
		Swal.fire("선택된 글이 없습니다.");
	}
}


// detailBoard.jsp 기능
// 1) 관리자에 의한 삭제 기능

function del(event) {
	event.preventDefault(); // HTML의 속성 이벤트를 막는다 
	var frm = document.forms[0];
	var con = confirm("선택된 글이 삭제됩니다.");
	console.log(typeof con, con);
	if(con) { // js에서 if문은 boolean이면 true/false로 동작, 
			 // 객체라면 객체가 존재한다면 true 따라서 반드시 타입에 맞춰 비교연산 필요
		frm.action="./realDelete.do";
		frm.method="post";
		frm.submit();		
	} else{
		Swal.fire("삭제가 취소되었습니다.");
	}
}

// 자신의 글인 경우 수정
function modify(event) {
	event.preventDefault();
	var frm = document.forms[0];
	var seq = document.querySelector("input[name='seq']").value;
	
	frm.action = "./modifyBoard.do?seq="+seq;
	frm.method = "get";
	frm.submit();	
}

// 답글 작성
function reply(event) {
	event.preventDefault();
	var frm = document.forms[0];
	var seq = document.querySelector("input[name='seq']").value;
	
	frm.action = "./replyBoard.do?seq="+seq;
	frm.submit();	
}



















