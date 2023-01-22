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

function deleteMultiple() {
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
            let rank = $('#rank').text(data.boxOfficeResult.dailyBoxOfficeList[0].rank);
            let title = $('#title').text(data.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
            let salesAcc = $('#salesAcc').text(data.boxOfficeResult.dailyBoxOfficeList[0].salesAcc);
            let openDt = $('#openDt').text(data.boxOfficeResult.dailyBoxOfficeList[0].openDt);

            let tr = $("<tr></tr>");
            let rk = $("<td></td>").text(rank);
            let tt = $("<td></td>").text(title);
            let sa = $("<td></td>").text(salesAcc);
            let od = $("<td></td>").text(openDt);

            tr.append(rk);
            tr.append(tt);
            tr.append(sa);
            tr.append(od);

            $('tbody').append(tr);
            console.log('코비');
        },
        error: function () {
            console.log('뭔가이상');
        }
    })

    $.ajax({
        async: true,
        url: 'https://dapi.kakao.com/v2/search/image',
        type: 'GET',
        headers: {
            Authorization: 'KakaoAK 5559977ec58da2ce68a5aba6c2027c04'
        },
        data: {
            query: tt
        },
        dataType: 'json',
        success: function (data) {
            console.log("카카오성공");
            let imgurl = data.documents[0].thumbnail_url;
            let myImg = $('<img/>').attr('src', imgurl);
            $('div').append(myImg);
        },
        error: function () {
            console.log("이상하다");
        }
    });
};
