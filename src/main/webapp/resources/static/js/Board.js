$('.summernote').summernote({
    tabSize : 2,
    height : 300
});


let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-update").on("click", () =>{
           this.update();
        });
    },

    update: function (){
        let id=$("#id").val();

        console.log("BoardUpdate 실행");

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        console.log("BoardUpdate 데이터 값 = " + data);
        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp){
            alert("수정이 완료되었습니다.");
            location.href= "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },



    deleteById: function (){
        let id = $("#id").text();
        console.log("게시글 삭제 ajax 호출");
        $.ajax({
            type : "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function (resp){
            alert("해당 : " + id + "의 게시글 삭제가 완료되었습니다.")
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
          if (resp.status == 200)
              alert("게시글 등록 성공")
            location.href ="/";
        }).fail(function(error){
            console.log(error.status);
            alert(JSON.stringify(error));
            alert("오류");
        });
    }
};

index.init();