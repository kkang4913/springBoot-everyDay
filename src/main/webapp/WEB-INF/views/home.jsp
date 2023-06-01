<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp" %>

<script>
$(document).ready(function() {
        loadUserList(1);

        $(document).on('click', '.page-link', function(e) {
            e.preventDefault();
            var page = $(this).data('page');
            loadUserList(page);
        });
    });
    function loadUserList(page) {
                $.ajax({
                    url: "/dummy/user",
                    method: "GET",
                    data: {page: page},
                    success: function(result) {
                        var userList = $("#userList");
                        var html="";
                        userList.empty();
                         result.data.forEach(function(user) {
                         html += '<div class="card m-2" style="width:100%">';
                         html += '<div class="card-body">';
                         html += '<h4 class="card-title">' + user.username + '</h4>';
                         html += '<p class="card-text">' + user.password + '</p>';
                         html += '<a href="#" class="btn btn-primary">상세보기</a>';
                         html += '</div>';
                         html += '</div>';
                        });
                            userList.append(html);

                        var pagination = $("#pagination");
                        pagination.empty();
                        console.log(result.data.length);
                        if (result.data.length > 0) {
                            var currentPage = result.currentPage;
                            var totalPages = result.totalPages;

                            console.log(result.data[0].currentPage);
                            console.log(result.data[0].totalPages);

                            if (currentPage > 1) {
                                var prevPage = currentPage - 1;
                                console.log(prevPage);
                                var prevHtml = '<li class="page-item"><a class="page-link" href="#" data-page="' + prevPage + '">Previous</a></li>';
                                pagination.append(prevHtml);
                            }

                            for (var i = 1; i <= totalPages; i++) {
                                var activeClass = (i === currentPage) ? 'active' : '';
                                var pageHtml = '<li class="page-item ' + activeClass + '"><a class="page-link" href="#" data-page="' + i + '">' + i + '</a></li>';
                                pagination.append(pageHtml);
                            }

                            if (currentPage < totalPages) {
                                var nextPage = currentPage + 1;
                                var nextHtml = '<li class="page-item"><a class="page-link" href="#" data-page="' + nextPage + '">Next</a></li>';
                                pagination.append(nextHtml);
                            }
                        }
                    }
                });
            }
        </script>


<div class="container" id="userList"></div>
<ul style="display: flex; justify-content: center;" id="pagination" class="pagination"></ul>


<%@ include file="../include/footer.jsp" %>
