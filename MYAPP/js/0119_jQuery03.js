
function myFunc() {
    // console.log($('ul > li').text());
    $('ul > li').each(function(idx,item) {
        // idx : 순번, 0부터 1씩 증가
        // item : 현재 사용되는 문서객체(document object)
        console.log($(item).text());
    });
}

function removeBtn() {
    $('button[disabled]').removeAttr()
}