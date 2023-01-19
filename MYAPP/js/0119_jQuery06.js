
// document라는 javascript가 제공하는 문서객체가 있어요
// document는 DOM을 지칭하는 문서객체이다.
// document가 준비되었다는 의미는 DOM 객체가 만들어졌다는 의미고
// browser가 HTML을 모두 읽어서 파싱까지 끝냈다는 의미
// 즉 browser가 HTML을 모두 읽은 시점을 의미

//$(document).on('ready', function(){ 의 축약형 (사용 X)
// $(document).ready(function(){
//     $('ul > li').click(function() {
//         alert('안녕하세요');
//     })
// })
// 축약  $() == document.ready
$(function(){
    $('ul > li').click(function() {
        // this의 의미는 크게 3가지로 나타낼수 있어요
        // 1. 일반 함수에서 this => window객체(전역객체)
        // 2. 객체의 함수로서 this => .함수를 호출한 객체를 지칭
        // 3. 생성자함수에서 this => 생성자 함수가 생성하는 객체를 지칭
        // ★★ 만약 jQuery event 처리에서
        // event handler안에서 this가 나오면 event source에 대한 문서객체
        alert($(this).text());
        // $(function() {}); 기본  // Handler for .ready() called.

    })
})

function myFunc() {
    event.preventDefault();
    alert('링크가 눌렸어요');
}