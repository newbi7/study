
function myFunc() {
    // 버튼을 누르면 KAKAO 이미지 검색을 해서..
    // 그 중 첫번쨰 이미지를 이용해서 DIV에 붙일거에요

    $.ajax({
        async: true,
        url: 'https://dapi.kakao.com/v2/search/image',
        type: 'GET',
        headers: {
            Authorization: 'KakaoAK 5559977ec58da2ce68a5aba6c2027c04'
        },
        data: {
            query: '냥이'
        },
        dataType: 'json',
        success: function(data) {
            console.log("성공");
            let imgurl = data.documents[0].thumbnail_url;
            let myImg = $('<img/>').attr('src',imgurl);
            $('div').append(myImg);
        },
        error: function() {
            console.log("이상하다");
        }
        
    })
}