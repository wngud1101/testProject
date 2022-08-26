$(document).ready(function() {
    //로그인 버튼 클릭 시
    $('#btn_login').click(function() {
        var formTest = $("form[name=login_form]");
        var login_form = $("form[name=login_form]").serialize();

        //빈 칸 확인
        var user_id = $("#user_id").val();
        var user_pwd = $("#user_pwd").val();

        if(!$("#user_id").val() || !$("#user_pwd").val()){
            alert('모두 입력해주세요');
        }else {
            if(!$("#user_id").val() || !$("#user_pwd").val()){
                alert('아이디를 입력해주세요');
                location.reload();
            }else {
                $.ajax({
                    type : "POST",
                    url : "login-test",
                    data : login_form,
                    dataType : "text",
                    async: false,
                    success : function(data) {
                        if (data == "true") {
                            window.location.href = 'login-success?user_id='+user_id;
                        }else {
                            alert('아이디나 비밀번호를 확인해주세요');
                        }
                    },
                    error : function(request, status, error) {
                        alert("code:" + request.status + "\n" + "error:" + error);
                    }
                }) //end ajax
            }
        }
    }); //end click

    //회원가입 버튼 클릭 시
    $('#signUp').click(function() {
        window.location.href = 'sign-up';
    });

    //모달 클릭 이벤트(open)
    $('#btn_modal').click(function() {
        var timer = setInterval(function(){
            var now = new Date();

            const h = String(now.getHours()).padStart((2, "0"));
            const m = String(now.getMinutes()).padStart((2, "0"));
            const s = String(now.getSeconds()).padStart((2, "0"));
            const curren_time = `${h}시:${m}분:${s}초`;

            $(".modal").css("display","block");
            $('input[name=modal_content]').attr('value',curren_time);

        },1000); //1초마다

        //종료버튼을 눌렀을 때
        $('#modal_close').click(function() {
            $(".modal").css("display","none");
            clearInterval(timer)
        });
    });

    //로그아웃 버튼 클릭 시
    $('#btn_logout').click(function() {
        //쿠키 가져오기
        $.ajax({
            type : "POST",
            url : "logout",
            dataType : "json",
            async: false,
            success : function(data) {
                window.location.href = 'login';
            },
            error : function(request, status, error) {
                alert("code:" + request.status + "\n" + "error:" + error);
            }
        }) //end ajax
    });

    //로그인 화면으로 돌아가기
    $('#btn_login_page').click(function() {
        window.location.href = 'login';
    });
}); // end ready