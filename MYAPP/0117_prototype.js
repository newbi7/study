
// // 일반객체
// var obj = {
//     name: '홍길동'
// }


// //함수선언문
// function myFunc(number) {
//     return 2 * number;
// }

// console.dir(myFunc);

///////////////////////////////////
// function myFunc(a, b) {
//    // return a + b; // arguments 유사배열객체가 있다. 
//    // 유사배열객체는 배열과 유사한 객체이고 배열과 생긴게 똑같고 사용도 비슷하게 한다.
//    // arguments 함수객체의 property.
//    // 가변인자함수를 구현하기 위해서 사용한다.
//    let result = 0;
//    for (let i=0; i<arguments.length; i++){
//     result += arguments[i];
//    }
//    return arguments[0] + arguments[1];
// }

// myFunc(10,20,30,40);
// console.log(sum(10,20));    //sum - 가변함수
// console.log(sum(10,20,30));
// console.log(sum(10,20,40,50,60));
// console.log(sum(10,20));
//////////////////////////////
// Es6에 들어오면서 Rest parameter라는걸 도입했다.
// 특히 Arrow function은 arguments가 없다.
// function sum(...args){
//     // args로 표현되는 진짜 Rest parameter는 Array이다.
//     let result = 0;
//     for (let i=0; i<args.length; i++){
//     result += args[i];
//     }
//     return result;  
// }
// console.log(sum(10,20));
// console.log(sum(10,20,30));
// console.log(sum(10,20,40,50,60));
//////////////////////////////////////////////
// function Circle(radius) {
//     this.radius = radius;
//     this.getArea = function() {
//         return Math.PI * this.radius ** 2;
//     }
// }
// const circle1 = new Circle(2);

// prototype객체로 생성해보자
// function Circle(radius) {
//     this.radius = radius;
//     Circle.prototype.getArea = function(){
//         return Math.PI * this.radius ** 2;
//     }
// }
// const circle1 = new Circle(2);
// const circle2 = new Circle(5);
// //같다.. => == 타입이 다르면 타입맞추고 비교
// //          === 타입이 같고 내용도 같은지 비교한다.
// console.log(circle1.radius === circle2.radius); // false
// console.log(circle1.getArea === circle2.getArea); // true

// function Circle(radius){
//     this.radius = radius;
//     Circle.prototype.getArea = function(){
//         return Math.PI * this.radius ** 2;
//     }
//     Circle.prototype.name = '홍길동'
// }

// const circle1 = new Circle(2);
// const circle2 = new Circle(5);

// console.log(circle1.name, circle2.name); // 홍길동, 홍길동

// // circle1.name = '신사임당'

// // console.log(circle1.name, circle2.name); // 신사임당, 홍길동 
// // // name '신사임당' 생성한 이후 property가 동적으로 추가 삭제가 된다.
// // proto 추가 삭제를 해보자
// circle1.__proto__.name = '신사임당'
// console.log(circle1.name, circle2.name); // 신사임당, 신사임당
// console.log(circle1.constructor === Circle); // true
// // console.log(circle1.__proto__.constructor === Circle); // true
////////////////////////////////////////////
//prototype은 결국 상속구조를 나타내기 위한 방법
// const obj = {} // 객체 literal로 만든 빈 객체

// const parent = { x : 1 }

// obj.__proto__ = parent

// console.log(obj.x);

// var obj = {
//     name : '홍길동'
// }
//////////////////////////////////////////
//생성자 함수를 이용한 객체 생성
// function Circle(radius){
//     thisl.radius = radius;
// }
// const circle1 = new Circle(1);

//console.dir(circl1);
//////////////////////////////////////////
// 유일한 예외가 하나 있다.
// 객체 만들떄 이렇게도 만들 수 있다.(예외적)
// const obj = Object.create(); 
//객체를 만들떄 상위 property 객체를 지정해서 만들 수 있다.
// null을 주면 ... 상위 prototype객체를 사용하지 않는다는 의미다.
// 이렇게 되면..._proto_를 아예 사용할 수 없다.
// 그래서 __proto__를 코드에 직접 사용을 권장하지 않는다.
// 다른방법으로 이용한다.
// 객체 literal로 생성
// const obj = {};

// const parent = {
//     x : 1
// }

// 상위 prototype객체를 얻어오기 위해서 아래처럼 하는건 좋지 않다.
// obj.__poroto__ 
// Object.getPrototypeOf(obj)  // prototype객체를 획득
// Object.setPrototypeOf(obj, parent);
///////////////////////////////////////////////
// non-constructor인 arrow function을 하나 만들어서
// 진짜 이 함수 객체의 prototype객체가 생성되지 않는지 확인하자.
// const person = (name) => {
//     this.name = name;
// }
// // person은 함수객체
// console.log(person.prototype); //undefined
///////////////////////////////
// 전체적인 그림그리기
// function Circle(radius) {
//     this.radius = radius
// }

// const circle1 = new Circle(5);
//////////////////////////////////
// function foo() {
//     x = 10; // 에러가 아닌... 전역변수로 만들어요
//             // window객체의 property로 등록
//             // 암묵적 전역(Impicit Global)이라고 한다.
//             // 자바스크립트 특유의 특징 - 자바스크립트는 변수선언을 하지 않아도 된다.(x)
//         }
// foo();

// console.log(x);  // 10

// // 유지보수에 도움이 안된다 -> 규칙있게 써보자!!
// 'use strict'; // 전역에서 제일 위에... 혹은 함수안에서 제일 위에
/////////////////////////////////////////
// var x = 100;

// if[true]{
//     let x = '홍길동'
// }

// function myFunc() {
//     console.log('hello')  //
// }

////////////////////////////////

//////////////////////////////////////
// const x = 1;

// function foo() {
//     const y = 2;

//     function bar() {
//         const z = 3;
//         console.log(x + y + z);
//     }
//     bar();
// }

// foo();
/////////
// 전역 execution context -> func함수의 execution context ->
// bar 함수의 execution context

// const x = 1;

// function outer() {
//     const x = 10;

//     const inner = function() {
//         console.log(x);
//     }
//     return inner; // 함수가 함수를 리턴한다.
// }

// const result = outer();

// result();  // 1이 되어야 우리가 알고 있는 정상적인 execution context stack의 동작이에요
// 그런데 10이 찍힌다. 왜그럴까? 이 현상을 클로져라고 부른다.

// function foo() {
//     const x = 1;
//     const y = 2;

//     function bar() {
//         const z = 3;

//         console.log(z);
//     }
//     return bar;
// }
// const result = foo();
// result();
// z -> closer 가 아니다. z가 x+z로 바뀌면 closer;

///////////////////////////////////////
// 클로져를 이용한 간단한 응용.

// const increase = function() {
//     let num = 0;
//     return ++num;
// }

// console.log(increase());    //1
// num = 10;
// 정보의 은닉은 되나 카운터로서 역할을 못한다.

const increase = (function() {
    let num = 0;

    return function(){
        return ++num;
    }
}());

console.log(increase());