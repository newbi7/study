
function myFunc() {
    // 이 안에 event객체가 자동으로 전달!
    // event객체는 event가 발생됐을떄 event의 세부정보를 가지고 있는 객체이다.
    // event.target -> event source를 지칭한다.
    $('div').text($('select > option:selected').text())
}