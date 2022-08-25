$(document).ready(function() {
    $('#btn_signUp').click(function() {

        //빈 칸 확인
        var id_check = $("#user_id").val();
        var pwd_check = $("#user_pwd").val();

        console.log(id_check);
        console.log(pwd_check);

        if(!$("#user_id").val() || !$("#user_pwd").val()){
            alert('모두 입력해주세요');
        }else {
            var signUp_form = $("form[name=signUp]").serialize();

            $.ajax({
                type : "POST",
                url : "signUp_check",
                data : signUp_form,
                dataType : "json",
                async: false,
                success : function(data) {
                    if (data == false) {
                        alert('회원가입 실패');
                    } else {
                        window.location.href = 'login';
                    }
                },
                error : function(request, status, error) {
                    alert("code:" + request.status + "\n" + "error:" + error);
                }
            }) //end ajax
        } //end if
    }); //end click

    //아이디 중복확인
    $('#btn_idCheck').click(function() {
        var user_id = $('#user_id').val();
        console.log('중복 : ' + user_id)

        if(!user_id){
            alert('아이디를 입력해 주세요');
        }else {
            console.log('테스트');
            $.ajax({
                type : "POST",
                url : "idCheck",
                data : {"user_id" : user_id},
                dataType : "json",
                success : function(data) {
                    console.log(data);
                    if (data == "true") {
                        alert('사용가능한 아이디입니다.');
                    }else {
                        alert('이미 있는 아이디입니다.');
                    }
                },
                error : function(request, status, error) {
                    alert("code:" + request.status + "\n" + "error:" + error);
                }
            }) //end ajax
        }
    });

}); //end ready