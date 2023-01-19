
function myFunc() {
    // 새로운 element를 jQuery를 이용해서 생성
    let li = $('<li></li>').text('이순신');
    // $('<li></li>').text(이순신).attr('id','myID').addClass('myStyle')
    // <li id="myID" class="myStyle">이순신<li>
    // 이렇게 만들어진 jQuery객체를 내가 원하는 곳에 부착할수있다.
    // 1. append()
    // 자식으로 붙이고 제일 마지막 자식으로 붙여요
    // $('ul').append(li);
    // 2. prepend() : 자식으로 붙이고 제일 앞에 자식으로 붙여요
    // $('ul').prepend(li);
    // 3. before() : 형제로 붙이고 앞에 붙여요
    // $('ul > li:last').before(li);
    // 4. after() : 형제로 붙이고 바로 다음에 붙여요
    // $('ul > li:first').after(li);

    // 새로운 이미지를 만들어 보아요
    // <img src='./js/car.jpg'>
    let myImg = $('<img />').attr('src','./image/car1.jpg'); // <img>
    $('ul').after(myImg);
}

function addHandler() {
    // 1. 이벤트 핸들러를 붙일 element를 찾아보아요
    // <li>강감찬</li>를 찾아요
    // 그 다음에 이벤트 핸들러를 특정 이벤트에 등록해요
    // $('ul > li:last').on('click', function() {
    //     console.log('강감찬이 클릭되었어요!');
    // })
    // 위의 형태가 기본이에요
    // 이걸 축약해서 써본다면??
    // $('ul > li:last').click(function(){
    //     console.log('강감찬이 클릭되었어요!');
    // })
    // 이벤트는 이렇게 jQuery로 등록하고 사용하는 방식으로 HTML에 이벤트 처리 내용이 나오지 않고 모두 
    // JavaScript code로 표현해요
    // 마지막으로 주의해야 할 점 2가지 + event에 대한 얘기를 정리해보아요

}
