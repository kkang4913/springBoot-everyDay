$(document).ready(function() {
    loadUBoardList(1);


    function loadUBoardList(page,searchKeyword,searchType) {
        $.ajax({
            url: "/dummy/board",
            method: "GET",
            data: {page: page,
                   searchKeyword: searchKeyword,
                   searchType: searchType
            },
            success: function (result) {
                let boardList = $("#boardList");
                let search = $("#search");
                let html = "";
                let searchHtml = "";
                search.empty();
                boardList.empty();
                searchHtml += '<div class="form-inline p-2 bd-highlight" >';
                searchHtml += '<select class="form-control" name="type" id="searchType">';
                searchHtml += '<option selected value="">전체</option>';
                searchHtml += '<option value="title">제목</option>';
                searchHtml += '<option value="content">내용</option>';
                searchHtml += '<option value="writer">작성자</option>';
                searchHtml += '</select>';
                searchHtml += '</div>';
                searchHtml += '<div class="form-inline p-2 bd-highlight">';
                searchHtml += '<div class="input-group">';
                searchHtml += '<input type="text" id="searchKeyword" class="form-control" placeholder="검색">';
                searchHtml += '<div class="input-group-append">';
                searchHtml += '<button id="btn-search" class="btn btn-primary search-keyword">Search</button>';
                searchHtml += '</div>';
                searchHtml += '</div>';
                searchHtml += '</div>';
                    result.data.forEach(function (board) {
                    html += '<div class="card m-2" style="width:100%">';
                    html += '<div class="card-body">';
                    html += '<h4 class="card-title">' + board.title + '</h4>';
                    html += '<p class="card-text">' + board.content + '</p>';
                    html += '<a href="/board/' + board.id +'" class="btn btn-primary">상세보기</a>';
                    html += '<span style="float:right">작성일: ' + board.createDate + '</span><br>\n'
                    html += '<span style="float:right">조회수: ' + board.count + '</span><br>\n'
                    html += '<span style="float:right">작성자: ' + board.userid.nickname + '</span>'
                    html += '</div>';
                    html += '</div>';
                });
                search.append(searchHtml);
                boardList.append(html);

                $(document).off('click','.page-link').on('click', '.page-link', function(e) {
                    e.preventDefault();
                    let page = $(this).data('page');
                    console.log(page);
                    loadUBoardList(page);
                });

                $(document).off('click','.search-keyword').on('click', '.search-keyword', function(e) {
                    e.preventDefault();
                    let searchKeyword = $("#searchKeyword").val();
                    let searchType = $("#searchType").val();
                    console.log(searchKeyword);
                    console.log(searchType);
                    loadUBoardList(page,searchKeyword,searchType);
                });


                var pagination = $("#pagination");
                pagination.empty();
                if (result.data.length > 0) {
                    var currentPage = result.currentPage;
                    var totalPages = result.totalPages;

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





