
function myFunc() {
    alert('버튼이 눌렸어요!');
}

function secondFunc(){
    alert('DIV가 눌렸어요!');
}

function myMouseEnter() {
    // 1. 이벤트가 발생한 객체를 찾아야 해요
    // $(event.target).css('color','red');
    // $(event.target).css('background-color','yellow');
    $(event.target).addClass('myEnterStyle');
}

function myMouseLeave() {
    // $(event.target).css('color','black');
    // $(event.target).css('background-color','White');
    $(event.target).removeClass('myEnterStyle');
}