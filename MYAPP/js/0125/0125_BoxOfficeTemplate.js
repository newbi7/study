$(function() {
    $('#selectDel').click(function() {
        $('[type=checkbox]:checked').parent().parent().remove();
    })
})

function searchBtn() {

    $.ajax({   
        // url: AJAX 호출할 서버쪽 프로그램 URL     
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        // async: 비동기방식으로 호출할것인지 동기방식으로 호출할 것인지 여부
        // 기본값은 true예요. 그런데 비동기가 모예요?
        async: true,  
        // 서버쪽 프로그램에게 전달할 데이터를 명시해요. 여러개의 데이터를 일반적으로
        // 전달하기 때문에 객체를 이용
        data: {
            key: 'e91acbaa99f0f1c9e650372d09c70f33',
            targetDt: $('#searchDate').val()
        },
        // type은 AJAX호출의 방식으로 GET, POST를 명시하시면 되요!
        type: 'GET',
        // 호출결과를 어떤 데이터형식으로 받을껀가요를 설정!
        // default값이 JSON이예요!
        dataType: 'json',
        // 이런 내용을 가지고 호출하면 결국 2중의 하나예요!
        // 성공하던가 에러가 나던가!!!
        // 만약 성공하면
        // 서버가 보내준 JSON 문자열을 jQuery가 JavaScript객체로 변환
        // 그리고 그 객체를 success의 함수의 인자로 전달!
        success: function(data) {
            $('tbody').empty();
            let arr = data.boxOfficeResult.dailyBoxOfficeList;
            $.each(arr, function(idx,item) {
                let title = item.movieNm;
                let rank = item.rank;
                let audi = item.audiAcc;
                let opendt = item.openDt;

                let tr = $("<tr></tr>");   

                let checktd = $("<td></td>")
                let delCheck = $('<input />')
                delCheck.addClass('form-check-input');
                delCheck.attr('type','checkbox');
                checktd.append(delCheck);

                let ranktd = $("<td></td>").text(rank) 
                let imgtd = $("<td></td>");
                let img = $("<img />");

                $.ajax({
                    async: true,
                    url: 'https://dapi.kakao.com/v2/search/image',
                    type: 'GET',
                    headers: {
                        Authorization: 'KakaoAK 1358282d53c290fdf77018c900369905'
                    },
                    data: {
                        query: title + ' 포스터'
                    },
                    dataType: 'json',
                    success: function(data) {
                        let imgurl = data.documents[0].thumbnail_url;
                        img.attr('src',imgurl);
                        imgtd.append(img)
                    },
                    error: function() {
                        alert('먼가 이상해요!!')
                    }
                });
                let titletd = $("<td></td>").text(title);
                let auditd = $("<td></td>").text(audi);
                let opentd = $("<td></td>").text(opendt);
                let deletetd = $("<td></td>")
                let delBtn = $("<button></button>").text('삭제');
                delBtn.addClass('btn btn-danger');
                delBtn.click(function() {
                    $(this).parent().parent().remove();
                });
                deletetd.append(delBtn);
    
                tr.append(checktd);
                tr.append(ranktd); 
                tr.append(imgtd); 
                tr.append(titletd); 
                tr.append(auditd); 
                tr.append(opentd); 
                tr.append(deletetd); 

                $('tbody').append(tr);                    
            });

        },
        error: function() {
            console.log('먼가 이상해요!');
        }
    });

    event.preventDefault();

}