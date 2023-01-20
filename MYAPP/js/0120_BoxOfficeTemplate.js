
function myFunc() {

    $.ajax({
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        async: true,
        data: {
            key: '756e6c8584bda7b0084ecb882f94a26a', targetDt: '20230119'},
            type : 'GET',
            dataType: 'json',
        success: function(data){
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
        },
        error: function() {
            console.log('뭔가이상');
        }   
        });
    };

function innerFunc() { 
    
}