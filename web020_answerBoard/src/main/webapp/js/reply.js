window.onload = function() {
	document.forms[0].onsubmit = (event) => {
		console.log("submit 버튼 작동");
		var chkContent = document.getElementById("checkContent").value;
		if(chkContent == 'y') {
			Swal.fire("답글내용은 필수");
			event.preventDefault(); 
		}
	}
	console.log("onload");
	document.getElementById("txtArea").onclick= () => {
		var obj1 = document.getElementById("hiddenContent").value; // 원본글
		var obj2 = document.getElementById("txtArea").value; // 작성글
		var tmpObj = obj2.replace("원본>","");
		if(obj1 == tmpObj) {
			document.getElementById("txtArea").value = "";
			document.getElementById("contxt").innerHTML = "내용";
			document.getElementById("checkContent").value = "n";
		}
	}
	document.querySelector("input[type=reset]").onclick = () =>{
		document.getElementById("contxt").innerHTML = "내용<br>(원본)";
		document.getElementById("checkContent").value = "y";
	}	
}