
let index= {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        }
        console.log(data);

        //ajax 통신을 통해 3개의 데이터를 json으로 변경하여 insert 요청
        $.ajax({
            type: "post", //요청하는 방식 ex) GET, POST 등등
            url: "/auth/api/joinProc", //호출 경로
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body에 담긴 데이터가 어떤 타입인지 명시
            dataType:"json", //서버로부터 응답 결과의 형태가 json 이라면 이를 javascript 오브젝트로 변경
            success: function (resp){
                if (resp.status == 500){
                    alert("회원가입에 실패하였습니다.")
                }else {
                    alert("회원가입이 완료되었습니다.");
                    location.href="/blog/";
                }
            },
            error: function (error){
                alert("회원가입 실패")
            }
        })
    }
}
    index.init();






