
// 자바스크립트는 ArrayList가 없다.
// JavaScript의 기본 자료구조는 Array(배열)이다.

// const myArray = [1, 2, 3, 4]

// 기본 for구문대신에 forEach를 이용.
// myArray.forEach(function(data) {
//    console.log(`결과는 : ` + data)
// })

// 경고창 alert()는 browser가 가지고 있는 API
// alert('소리없는 아우성')  -> 실행되지 않는다 ReferenceError: alert is not defined
// 서버 연결 이후부터 실행되기 때문에 alert에서 멈춰서 block 상태가 된다.

// 변수를 선언
// console.log(result);
// var result = 100;
// 에러가 안뜬다.
// Hoisting 때문에 -> JavaScript의 실행은 2개의 Phase로 실행된다. 
// 1번쨰 Creating Phase -> 2번쨰 Excution Phase라고 한다.
// 모든 선언구문을 찾아서 식별자를 메모리에 매핑(변수화시키고) 끝나면 다시 돌아가서 코드를 실행한다.

// VAR keywrod는 같은 scope내에서 중복선언이 가능하다.
// var x = 100;
// var y = 200;

// var x = 300;  // var가 없는걸로 간주된다.
// x = 300;
// var y; // 이런경우는 무시

// var keyword는 function level scope만을 local scope로 인정한다.
// var x = 1;

// if (true) {
//     var x = 100;
// }
// console.log(x); // 100 function level scope라서
///////////////////////////////////////////////////
// Java는 함수가 없고 대신 method가 있다. method 는 class안에 위치한다. 
// JavaScript는 함수형 언어라서 함수가 존재한다. 
// 독립적으로 존재하기 때문에 함수를 만들떄는 function keyword를 사용한다.
// 당연히 함수이름이 있어야 하니 식별자를 붙여준다. 인자와 실행코드가 block으로 표현
// var x = 1;

// function myFunc() {
//     var x = 200;
//     console.log(x); // 1
// }

// console.log(x); // 1
// myFunc();
// console.log(x); // 200
// 출력 1 - 200 - 1
//////////////////////////////////////////////////////
// 이번에는 let ES6에서 도입되었고 block level scope이고 알고 있는 변수방식을 이용한다.
// 같은 scope내에서 중복선언이 안된다.
// let result = 100;
// let result = 50;
// block level scope

// let score = 50;
// {let score = 100;
//  let myVar = 10;
// }
// console.log(score); // 50
// console.log(myVar); // Error
//////////////////////////////////
// let i = 100; // i는 전역변수(global variable)
// function myFunc() {
//     let i = 10; // i는 지역변수(local variable)

//     for (let i = 0; i<3; i++) {
//         console.log(i); // 0 1 2
//         process
//     }
// }
// myFunc();
// console.log(i); // 100

// console.log(x);  //undefined가 출력 안되고 error가 출력된다.
// let을 선언된 경우에는 hoisting 은 일어나지만 x를 사용할수 있게되는 시점은 x를 초기화한 이후부터 사용할 수 있다.
// 이 영역을 Temporal Dead Zone(TDZ) 라고 부른다.
// let x = 100;

// hosting은 var, let, const 전부 일어난다.
// let myVar = 1; // 전역변수(global variable) 
// {
//     console.log(myVar);
//     let myVar = 100; // hoisting 되기 때문에 그다음 TDZ가 생겨서 오류가 뜬다
// }
// console.log(myVar);