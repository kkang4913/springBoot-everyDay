

$(document).ready(function() {
    loadUBoardList(1);
    function loadUBoardList(page) {
        $.ajax({
            url: "/dummy/board",
            method: "GET",
            data: {page: page},
            success: function (result) {
                var boardList = $("#boardList");
                var html = "";
                boardList.empty();
                result.data.forEach(function (board) {
                    html += '<div class="card m-2" style="width:100%">';
                    html += '<div class="card-body">';
                    html += '<h4 class="card-title">' + board.title + '</h4>';
                    html += '<p class="card-text">' + board.content + '</p>';
                    html += '<a href="/blog/board/' + board.id +'" class="btn btn-primary">상세보기</a>';
                    html += '</div>';
                    html += '</div>';
                });
                boardList.append(html);

                $(document).on('click', '.page-link', function(e) {
                    e.preventDefault();
                    var page = $(this).data('page');
                    console.log(page);
                    loadUBoardList(page);
                });



                var pagination = $("#pagination");
                pagination.empty();
                if (result.data.length > 0) {
                    var currentPage = result.currentPage;
                    var totalPages = result.totalPages;

                    console.log(currentPage);
                    console.log(totalPages);


                    if (currentPage > 1) {
                        var prevPage = currentPage - 1;
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
});