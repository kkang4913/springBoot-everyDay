    $(document).on('click', '.page-link', function(e) {
        e.preventDefault();
        var page = $(this).data('page');
        console.log(page);
        loadUBoardList(page);
    });
