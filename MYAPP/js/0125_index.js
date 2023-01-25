
alert('경고창이에요!');

// 대표적인 blocking 함수, 함수를 호출한 곳에서 프로그램의 수행이 일시 중지되는 함수

let result = $.ajax({
    async: false,
    url: '',
    type: 'get',
    data: {},
    success: function(){
    }
    
// ajax 호출을 해서 서버쪽 프로그램이 실행되고 그 결과가 Json으로 보내주는데 시간이 걸린다.
// 그런데 그 시간을 기다리지 않고 ajax()호출은 바로 리턴된다. 당연히 리턴되는 값은 ajax()의 결과값이 아니다.
// event driven 방식으로 결과를 받는다.
// 1분뒤에 호출될지 2분뒤에 호출될지 모른다.
// asycn: true(true : 비동기), 동기 비동기를 나타낸다.