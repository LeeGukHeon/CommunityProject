
let idValid = false;
let pwValid = false;
let nameValid = false;

$(function(){
	$('#id').keyup(function(){
		idValid = false;
	})
})

$(function(){
	$('#pw').keyup(function(){
		if($('#pw').val() != ""){
			pwValid = true;
		}else{
			pwValid = false;
		}
	})
})

$(function(){
	$('#name').keyup(function(){
		if($('#name').val() != ""){
			nameValid = true;
		}else{
			nameValid = false;
		}
	})
})

$(function(){
	$('#duplicate').click(function(e){
		e.preventDefault();
		if($('#id').val() != ""){
			let id = $('#id').val();
			$.ajax({
				url:"/duplicate",
				type:'post',
				data: {"id":id},
				success:function(data){
					if(data == 0){
						alert("사용 가능한 아이디입니다");
						idValid=true;
						$('#id').prop("readonly", true);
						$('#id').css("cursor", "default");
						$('#id').css("background-color", "#ccc");
						$('#id').css("border","1px solid black");
					}else{
						alert("중복된 아이디입니다");
						idValid=false;
					}
				},error:function(){
					alert("error");
				}
			})
		}else{
			alert("아이디를 입력해주세요");
		}
	})
})

$(function(){
	$('#btn').click(function(e){
		e.preventDefault();
		let ischecked = $("#chkbox").prop("checked");
		if(!idValid){
			alert("아이디 중복검사를 해주세요");
		}else if(!pwValid){
			alert("비밀번호를 입력해주세요");
		}else if(!nameValid){
			alert("닉네임을 입력해주세요");
		}else if(!ischecked){
			alert("약관에 동의를 해주세요");
		}else{
			$(this).unbind('click').click();
			alert("회원가입 성공");
		}
	})
})

function termsChk (){
	let ischecked = $("#chkbox").prop("checked");
	if(ischecked){
		alert("체크")
	}
}

function before(event){
	event.preventDefault();
	let ischecked = $("#chkbox").prop("checked");
	if(!ischecked){
		alert("약관 동의필요")
	}
}