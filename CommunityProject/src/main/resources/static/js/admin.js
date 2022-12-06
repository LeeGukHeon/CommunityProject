/**
 * 
 */
 
$(function(){
	$(".delete").click(function(){
	let result = confirm("정말 유저를 탈퇴시키시겠습니까?");
	if(result){
		let tr = $(this).parent().parent();
		let td = tr.children();
		let userid = td.eq(1).text();
		location.href="/admin/deleteuser?id="+userid;
		alert("권한 수정 성공");
	}
})
})

$(function(){
	$(".modify").click(function(){
		let result = confirm("유저 권한을 수정하시겠습니까?");
		if(result){
			let tr = $(this).parent().parent();
			let td = tr.children();
			let userid = td.eq(1).text();
			let auth = td.eq(3);
			let authval = auth.find('option:selected').val();
			location.href="/admin/updateuserauth?id="+userid+"&auth="+authval;
			alert("권한 수정 성공");
		}
	})
})
