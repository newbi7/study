
// var x = "Hello";    // 전역변수(global scope)

// //함수 선언문
// function foo() {
//     var x = "World"; // 지역변수(local scope)
    
//     console.log(y);
//     // 변수를 찾을떄 현재 scope로 부터 상위 scope로 올라가면서 변수를 찾아요

//     console.log(x);
//     // JavaScript Engine 이 이 x는 과연 x인가
//     // identifier resolution(식별자 결정) => Scope
//     // 두개의 x 는 이름은 같지만 scope가 달라요 scope => namespace
// }
// foo();
// console.log(x);

/////////////////////////////////////////////////
// var x = 1;

// function foo() {
//     var x = 10;
//     bar();
// }

// function bar(){
//     console.log(x);
// }
// foo();

////////////////////////////////////////////////
// const는 재 할당이안 된다. - constant(상수)
// const obj = {
//     // property
//     myName : '홍길동',
//     myAge : 20
// }
// // console.log(obj.myName);  //홍길동
// console.log(obj.__proto__); // = ([[Prototype]])

/////////////////////////////////////////////////////
// const person = {
//     name : '홍길동',
//     age : 20
// }
// console.log(person);
// console.log(Object.getOwnPropertyDescriptor(person));

/////////////////////////////////////////////////////
// const person = {}
//     Object.defineProperty(person, 'firstName', {
//         valuep : '흥',
//         writable : true ,
//         enumerable : true,
//         configurable : true   
//     })
// ==
// const person1 = {
//     firstName : '홍'
// }
// console.log((person.firstName)); // 홍
// person.firstName = '최';
// console.log(person.firstName); // 최

// for(let idx in person) {
//     console.log(idx);
// }
//////////////////////////////////////////////////
// new라는 키워드로 생성자 함수를 호출할 수 있어요
// const person = new Object() // {}
// person.name = '홍길동';
// person.age = 20;
// console.log(person);

// const strObj = '소리없는 아우성'
// const strObj = new String('소리없는 아우성!')

// console.log(typeof strObj); // Object
// // 유사배열객체 -> 진짜뱅려과 동일한데 함수를 가지고 있지 않다.
// // index같은 기능은 있지만 배열이 가지는 기능은 쓸 수 없다.

// console.log(strObj); // 출력 = String {'소리없는 아우성!'}

// const strObj1 = {
//     name : '홍길동'
// }
// console.log(typeof strObj1);
// console.log(strObj1);

////////////////////////////////////////////
// // const numObj = new Number(100);
// // console.log(numObj);
// const dateObj = new Date();
// console.log(dateObj.getDate());
// console.log(dateObj.toLocaleString());

////////////////////////////
// 사용자 정의 생성자 함수 // 관용적으로 첫글자를 대문자로 쓴다.

// 함수 선언문
// 1. 일반함수 , 2. 생성자 함수
// function Person() {

// }

// const person1 = new Person();
// const person2 = {};
// console.log(person1); // Person{};
// console.log(person2); // {};
// 일반적인 생성자 함수의 형태
// 1. this keyword가 나온다.
//    this 의 의미는 이 생성자 함수로 만들어지는 객체를 지칭
// 2. return 구문이 없다.
// function Circle(radius) {
//     this.radius = radius;
//     this.getDiameter = function() {
//         return 2 * 3.14 * radius;
//     }
// }   
// function getResult(a, b) {
//     return a + b;
// }

// let circle1 = new Circle(5); // 객체 생성
// let circle2 = new Circle(10); // 객체 생성
// console.log(circle1);
// console.log(circle1.getDiameter);
// console.log(circle2);

// // 함수 선언문
// function foo() {
//     console.log(this); // 모양가지고 판단이 되진 않는다.
// }
// foo(); // 일반함수로 호출 => this는 전역객체

// const obj = {

//      foo,    // foo = foo; // 객체가 가지고 있는 함수 ES6의 축약표현
//      // 이렇게 축약표현으로 되고 있는 함수를 메소드로
// }
// obj.foo();   
// const obj1 = new foo();
// // Window {window: Window ........}, {foo: f} , foo{}
// //////////////////////
// // 생성자 함수는 new keyword와 함께 사용되고 생성자 함수는 그 안에 리턴구문이 없다.
// // 그럼에도 불구하고 만들어진 객체가 묵시적으로 리턴된다.
// function Circle(radius){
//     this.radius = radius;
//     // return this;
//     // return {};
//     return 100;
// }
// const circle1 = Circle(10) // 일반함수로 호출
// // 함수가 호출됬는데 리턴이 없으면?? 묵시적으로 undefined가 리턴된다.
// console.log(circle1); //undefined

// const circle1 = new Circle(10)// return{} -> 결과 {}
// console.log(circle1); 객체냐 primitive type이냐에 따라 달라진다. // Circle {radius: 10}

///////////////////////////////
// var myName = '홍길동' // window객체의 property 등록
// let myAge = 20;      // window객체에 등록은 안된다.
// // 전역객체일떄 var써야 한다.
// console.log(myName);
// console.log(window.myName);

// function foo() {
//     console.log(this);
// }
// foo();

///////////////////////////////////////
// function foo() {

// }

// // 첫번째 phase에서 foo라는 변수가 생성(묵시적으로)
// foo.myName = '홍길동'
// foo.myFunc = function() {
//     console.log(this);
// }

// console.log(foo.myName); // 홍길동
// console.log(foo.myFunc); 
// foo.myFunc();
/////////////////////////////////////
// function foo() {
// }

// // foo는 일반함수로 호출이 가능하기 때문에
// // 기본적으로 [[Call]]를 가지고 있는 함수예요
// // foo는 callable
// foo(); // 이렇게 호출하면 내부적으로 [[Call]]이 호출된다.
// var myVar = 100;
// myVar(); // [[Call]]이 없기 떄문에 오류가 발생한다.
////////////////////////////////////////////////////////////
// [[Construct]] => 함수객체에 붙을 수 있다.
// 1. [[Construct]] 내부 메소드가 붙은 경우
//      => 함수선언문, 함수표현식, 클래스
//      => 객체를 생성할 수 있는 함수형태.. new 사용
//      => constructor라고 불러요!
// function myFunc() {
// }
// var myFunc = function () {}
// new myFunc (); //[[Construct]] 호출
// 2. [[Construct]] 내부 메소드가 없는 경우
//      => 메소드인경우(ES6의 메소드표현식)
//      => Arrow-Function 
//      => non-constructor

// 함수선언문, callable([[Call]] 가지고 있다.)
// constructor([[Construct]]) 가지고 있다.)
// function foo() {

// }
// foo();          //OK
// new foo();      //OK
// // 함수표현식
// const bar = function() {

// }
// bar();      //OK
// new bar();  //OK

// const obj = {
//     x: function() {} //method라고 하지 않는다
// }
// new obj.x(); // method가 아니기 때문에 객체 생성이 가능하다. //constructor

// const myFunc = (a,b) => {return a+b}
// myFunc(); // 가능 [[Call]]이 있어요!
// new myFunc(); //Error!!!

// var obj = {
//     // myFunc : function() {}
//     // 이 형태가 축약형이고 메소드라고 불려요!
//     myFunc(){

//     },
//     name : '홍길동'
// }

// new obj.myFunc(); //객체생성이 안된다.

// //함수선언문 - constructor
// function add(x,y) {
//     return x + y;
// }
// let instance = new add(); // OK
// console.log(instance); // add {}

// // 함수선언문
// function createUser(name, age) {
//     // return {name, age} //return {name: name, age: age}
//     // this.name = name;
//     // this.age = age;
//     return {name, age}
// }
// // let inst = new createUser('홍길동', 20);
// console.log(inst);

// // 함수선언문 - 생성자함수
// function Circle(radius) {
//     this.radius = radius;
//     this.getDiameter = function() {} // 메서드 아니다.
//     console.log('haha');
// }

// const circle1 = Circle(10);
// // console.log(circle1); // undefined

// // console.log(radius); // 10;
// // circle1.getDiameter() // Error!
//////////////////////////////////////////
// var obj = {
//     name : '홍길동'
// }
// console.log(obj);
// console.dir(obj);

function square(number){
    return number * number;
}
console.log(square);
console.dir(square);