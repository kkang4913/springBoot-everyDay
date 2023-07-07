 $(document).ready(function() {
   // Ajax 요청 보내기
   $.ajax({
     url: "/dummy/user",
     method: "GET",
     success: function(result) {
       var userList = $("#userList"); // 사용자 목록 영역
       var html = "";
       result.forEach(function(user) {
         html += '<div class="card m-2" style="width:100%">';
         html += '<div class="card-body">';
         html += '<h4 class="card-title">' + user.username + '</h4>';
         html += '<p class="card-text">' + user.password + '</p>';
         html += '<a href="#" class="btn btn-primary">상세보기</a>';
         html += '</div>';
         html += '</div>';
       });
       userList.append(html);
     }
   });
 });