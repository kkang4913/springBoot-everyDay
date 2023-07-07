$('.summernote').summernote({
    tabSize : 2,
    height : 300
});


let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            this.save();
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