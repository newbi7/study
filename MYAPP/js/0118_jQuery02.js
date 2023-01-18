function myFunc() { 
    console.log($('#apple').text());
    console.log($('#apple + li').text());
    console.log($('ul > li.myList').text());
    
    console.log($('form > input').attr('id'));

    console.log($('ol > li:first').text());
    console.log($('ol > li:first + li').text());
    console.log($('ol > li:last').text());
    //    1. 전체 선택자(Universal selector) => *
    //  $('*').css('color', 'red');
    //    2. 타입 선택자(Type selector) => tag이름
    //  $('li').remove(); // $('<li>') 아니다.
    //    3. 아이디 선택자(ID selector) => id명으로 선택
    //    모든 tag에는 id라는 속성이 붙을 수 있어요
    //    id값은 중복이 허용되지 않아요(unique)
    //  let name = $('#kang').text(); // text()는 tag사이에 있는 값을 알아낼수 있다.
    //  console.log(name);
    //    4. 클래스 선택자(class selector) => class명으로 선택
    //    모든 tag에는 class라는 속성이 붙을 수 있다. 중복을 허용하고 style을 지정할때 사용하는 속성이다.
    //  $('.myClass').css('background', 'yellow');
    //    5. 구조 선택자(자식선택자와 후손선택자)
    //    자식 선택자는 기호를 > 를 이용해요 그리고 다른 selector와 혼합해서 사용
    //    text()는 값을 알아내는 method예요!
    //    text('변경할값')는 값을 변경하는 method이에요!
    //  $('div > div').text('소리없는 아우성!')
    //    후손선택자는 공백을 이용해요
    //  %('div li').remove();
    // 6. 동위선택자 ( + , ~ )
    //    + :바로 뒤에 나오는 형제를 지칭
    //    - : 내 뒤쪽에 나오는 모든 형제를 지칭!
    //  alert($('#kang + li').text());
    //  $('#shin ~ *').remove();
    // 7. 속성선택자 : 속성가지고 찾을 수 있어요! => []
    //  console.log($('[type]').attr('value'));
    }