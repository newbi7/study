
function innerFunc() {
    alert('버튼이 클릭클릭!');
    event.stopPropagation();
}

function outerFunc() {
    alert('DIV가 클릭클릭!');
}