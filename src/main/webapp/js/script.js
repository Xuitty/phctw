var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.6.2.js';
document.getElementsByTagName('head')[0].appendChild(script);
var a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
$("#body").mousemove(function (){
    detect();
});

$("#body").keyup(function(){
    detect();
});
		

function ajax(){
			$.ajax({
				url : "ajax", //请求地址
				type : "get", //请求方法
				data : {
					"sno" : $("#sno").val(),
					"sid" : $("#sid").val()
				}, //要发送的数据,相当于表单提交的数据，json形式。
				dataType : "text", //期待返回的数据类型，也可以理解为请求的数据类型
				error : function() { //发生错误时的处理

				},
				success : function(data) { //成功时的处理。参数表示返回的数据
					if (data == "成功") {
						// document.getElementById('submit').style.display='block';
						a = 1;
					} else {
						a = 0;
						// document.getElementById('submit').style.display='none';
					}
				}
			})
		}

        
		function detect() {
			ajax();
			if($("#sid").val() == '' || $("#sname").val() == '' || $("#sbday").val() == '' || $("#spwd").val() == '' || $("#smail").val() == '' || $("#sno").val() == ''){
			$("#result").text("有欄位沒填");
			document.getElementById('submit').style.display = 'none'
			}else if($("#sname").val().length < 2 || $("#spwd").val().length < 6 || $("#sbday").val().length != 10 || $("#sno").val().length < 4  ){
				$("#result").text("長度錯誤");
			document.getElementById('submit').style.display = 'none'

			}else if (a == 0 && $("#sid").val() != '' && $("#sname").val() != '' && $("#sbday").val() != '' && $("#spwd").val() != '' && $("#smail").val() != '') {
				document.getElementById('submit').style.display = 'none'
				$("#result").text("學號重複或身分證有問題");
			}else if (a == 1 && $("#sid").val() != '' && $("#sname").val() != '' && $("#sbday").val() != '' && $("#spwd").val() != '' && $("#smail").val() != '') {
				document.getElementById('submit').style.display = 'block'
				$("#result").text("");
			}
			else {
				document.getElementById('submit').style.display = 'none'
			}
			// console.log("123");
		}
