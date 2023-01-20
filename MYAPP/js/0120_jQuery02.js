
// 함수선언문 - Event Handler
function myFunc() {
   
    // jQuery를 이용해서 AJAX 호출
    // 호출할떄 여러가지 정보를 넣어야 한다.
    // 여러가지 정보를 객체literal이용해서 객체로 만들어서 인자로 전달.
    $.ajax({
        // url : AJAX 호출할 서버쪽 프로그램 url
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        // async: 비동기방식으로 호출할것인지 동기방식으로 호출할 것인지 여부, 기본값은 true
        async: true,
        data: {
            key: '756e6c8584bda7b0084ecb882f94a26a', targetDt: '20230119'},
        // type은 AJAX호출의 방식으로 GET, POST를 명시하시면 되요!
            type : 'GET',
        // 호출결과를 어떤 데이터형식으로 받을껀가요를 설정!
        // default값이 JSON이에요!
            dataType: 'json',
        // 이런 내용을 가지고 호출시 성공 또는 실패
        // 서버가 보내준 JSON 문자열을 jQuery가 JavaScript객체로 변환
        // 객체를 success 함수의 인자로 전달
        success: function(data){
            console.log(data.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
            $('div>div').text(title);
        },
        error: function() {
            console.log('뭔가이상');
        }   
        });
    };
