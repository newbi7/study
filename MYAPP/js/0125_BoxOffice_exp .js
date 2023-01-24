
// alert('경고창이에요!');

// // 대표적인 blocking 함수, 함수를 호출한 곳에서 프로그램의 수행이 일시 중지되는 함수

// let result = $.ajax({
//     async: false,
//     url: '',
//     type: 'get',
//     data: {},
//     success: function(){
//     }
    
// // ajax 호출을 해서 서버쪽 프로그램이 실행되고 그 결과가 Json으로 보내주는데 시간이 걸린다.
// // 그런데 그 시간을 기다리지 않고 ajax()호출은 바로 리턴된다. 당연히 리턴되는 값은 ajax()의 결과값이 아니다.
// // event driven 방식으로 결과를 받는다.
// // 1분뒤에 호출될지 2분뒤에 호출될지 모른다.
// // asycn: true(true : 비동기), 동기 비동기를 나타낸다.

function searchBtn() {
    // 날짜를 가져와서
    // AJAX 호출하면 된다.

    $.ajax({
        async: true,
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        type: 'GET',
        timeout: 3000, // 천분의 1초 단위로 초를 적는다.
        data: {
            key: '756e6c8584bda7b0084ecb882f94a26a',
            targetDt: ($('[type=date]').val()).replace(/-/g, '')
        },
        dataType: 'json', // default값이 json이라서 안써도 된다.
        success: function (data) {
            // 성공하면 당연히 서버는 결과값을 JSON 문자열로 전달한다.
            // 이 문자열을 받아서 분석해서 결과처리를 해야한다.
            // 객체로 변경해서 함수의 인자로 전달해준다.
            let arr = data.boxofficeResult.dailyBoxOfficeList;
            // 배열안의 각 요소에 대해서 함수를 호출(Callback)한다.
            $.each(arr, function (idx, item) {
                // idx : 반복할때마다 숫자가 증가하는 index
                // item : 반복할때마다 추출되는 배열 안에 있는 원소
                // console.log(item.movieNm);
                // 데이터를 정상적으로 가져오면 화면처리하면된다.
                // 태그를 만들어 보아요
                //     <tr>
                //   <td>1,001</td>
                //   <td>random</td>
                //   <td>data</td>
                //   <td>placeholder</td>
                //   <td>text</td>
                //   <td><button class="btn btn-danger" onclick="myFunc()">삭제<button></td>
                // </tr>
                let tr = $("<tr></tr>"); // <tr></tr>
                let ranktd = $("<td></td>").text(item.rank); // <td>1</td>
                let postertd = $("<td></td>");
                let posterImg = $("<img />");
                postertd.append(posterImg);

                // 이미지를 가져오기 위한 AJAX를 호출해야 한다.
                $.ajax({
                    async: true,
                    url: 'https://dapi.kakao.com/v2/search/image',
                    type: 'GET',
                    headers: {
                        Authorization: 'KakaoAK 5559977ec58da2ce68a5aba6c2027c04'
                    },
                    data: {
                        query: item.movieNm + ' 포스터'
                        
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

                let titletd = $("<td></td>").text(item.title);
                let audtd = $("<td></td>").text(item.audiCnt);
                let opentd = $("<td></td>").text(item.openDt);
                let deltd = $("<td></td>");
                let delBtn = $("<button></button>").text('삭제').addClass('btn btn-danger');
                deltd.append(delBtn);
                // 삭제버튼에 클릭 이벤트에 대한 이벤트 핸들러를 등록해준다.
                delBtn.click(function() {
                    // jQuery event 처리방식에서 이벤트 핸들러에 this가 등장하면
                    // event source 문서객체를 지칭해요
                    $(this).parent().parent().remove();
                })
                
                tr.append(ranktd);
                tr.append(postertd);
                tr.append(titletd);
                tr.append(audtd);
                tr.append(opentd);
                tr.append(deltd);
                $('tbody').append(tr);
            })
        },
        error: function () {
            console.log("이상하다");
        }
    })
    // 지금 클릭한게 ..<a>이다.
    // default event를 가지고 있어서 막아줘야한다.
    event.preventDefault();
}


