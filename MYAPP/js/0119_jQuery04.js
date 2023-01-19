
function myFunc() {
    // 삭제
    // $('#innerDiv').remove();

    // 숨김
    // $ ('#innerDiv').hide();
    // 숨겨져 있는거 다시 보일려면 .show();

    // 삭제(후손만 삭제)
    $ ('#innerDiv').empty();
}

function display_input {
    alert($('#myID').val()); 
}