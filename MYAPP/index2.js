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
let myVar = 1; // 전역변수(global variable) 
{
    console.log(myVar);
    let myVar = 100; // hoisting 되기 때문에 그다음 TDZ가 생겨서 오류가 뜬다
}
console.log(myVar);