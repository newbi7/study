
$(function () {
    $('#searchButton').on('click', searchData);
    $('#deleteButton').on('click', deletecheck);

    var today = new Date();
    $('#searchdatefield').val(formatDateStr(today, true));
});

function formatDateStr(dateStr, toISO) {
    const dateObj = new Date(dateStr);
    const year = dateObj.getFullYear();
    const month = ('0' + (dateObj.getMonth() + 1)).slice(-2); // 뒤에서부터 두 글자를 복사하여 새로운 문자열 리턴
    const date = ('0' + dateObj.getDate()).slice(-2);
    if (toISO) {
        return `${year}-${month}-${date}`;
    } else {
        return `${year}${month}${date}`;
    }
}

function deletecheck() {
    const targetKeysList = [];

    $('input[type="checkbox"].delete-checkbox:checked').each(function () {
        targetKeysList.push($(this).parents('tr').attr('data-key'));
    })

    targetKeysList.forEach((key) => {
        $(`tr[data-key="${key}"]`).remove();
    })
}

function deleteOne() {
    const targetKey = $(this).parents('tr').attr('data-key');
    $(`tr[data-key="${targetKey}"]`).remove();
}

function searchData() {

    const targetDate = formatDateStr($('#searchdatefield').val());

    $.ajax({
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        async: true,
        data: {
            key: '756e6c8584bda7b0084ecb882f94a26a', targetDt: targetDate
        },
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log('코비');
            const list = data.boxOfficeResult.dailyBoxOfficeList;
            list.forEach((item, key) => {
                const checkbox = $('<input />').attr('type', 'checkbox').addClass('delete-checkbox');
                const deleteBtn = $('<button></button>').text('삭제').addClass('btn').addClass('btn-danger').click(deleteOne);
                const checkTd = $('<td></td>').append(checkbox);
                const delBtnTd = $('<td></td>').append(deleteBtn);
                const rankTd = $('<td></td>').text(item.rank + '위');
                const posterTd = $('<td></td>');
                const titleTd = $('<td></td>').text(item.movieNm);
                const audiTd = $('<td></td>').text(Number(item.audiAcc).toLocaleString() + '명');
                const openDtTd = $('<td></td>').text(item.openDt);
                const tr = $('<tr></tr>');
                tr.append(checkTd);
                tr.append(delBtnTd);
                tr.append(rankTd);
                tr.append(posterTd);
                tr.append(titleTd);
                tr.append(audiTd);
                tr.append(openDtTd);
                tr.attr('data-key', key);

                $.ajax({
                    async: true,
                    url: 'https://dapi.kakao.com/v2/search/image',
                    type: 'GET',
                    headers: {
                        Authorization: 'KakaoAK 5559977ec58da2ce68a5aba6c2027c04'
                    },
                    data: {
                        query: item.movieNm
                    },
                    dataType: 'json',
                    success: function (data) {
                        console.log("카카오성공");
                        let imgurl = data.documents[0].thumbnail_url;
                        let myImg = $('<img/>').attr('src', imgurl);
                        posterTd.append(myImg);
                    },
                    error: function () {
                        console.log("이상하다");
                    }
                });
            })
        },
        error: function () {
            console.log('뭔가이상');
        }
    })

};
