// primitive type

// 1. number type // 정수와 실수를 구분하지 않아요
let myVar = 1.0 // 실수 -> number
console.log(myVar == 1); //true
console.log(3 / 2); //1.5
console.log(10 / 0); // Infinity(무한대)
console.log(10 * 'Hello'); // NaN(Not A Number) // 산술연산불가

// 2. String type // 문자열 타입
var myStr = '아우성!!' // 일반적인 형태
myStr = "소리없는"     // 이것도 사용 가능
myStr = `이것은 소리없는 \n 아우성` // 이렇게도 사용가능
myStr = `이것은
소리없는
아우성`                         // \n 과 `엔터키입력`이 같다.
let test = 100
myStr = `현재 test의 값은 ${test}입니다.`
myStr = '현재 test의 값은' + test + '입니다.'
console.log(myStr);

// 3. boolean type - true, false 두개의 값만 제공한다.
var test1 = true

// 4. undefined type - 사용할 수 있는 값이 undefined만 있다. (JavaScript Engine이 사용)

// 5. null type - 사용할 수 있는 값이 null만 있다. 이 값은 우리가 사용하는 값이다.
test = null;

// 6. Symbol type 
// ES6(ECMAScript 2015)에 추가된 타입이에요 (Unique한 값 표현)
let mySymbol = Symbol();
console.log(mySymbol); // Symbol() // 유일한 값은 맞지만 무엇인지 확인불가
let mySymbol1 = Symbol("소리없는 아우성!");
let mySymbol2 = Symbol("소리없는 아우성!");
console.log(mySymbol1 == mySymbol2); // false

// Reference Type(함수, 객체, 배열)은 수업이 진행되면서 알아보아요
let str = 'hello';
console.log(str[0]); // h ??
console.log(str.length); //  5
console.log(str.toUpperCase()); // HELLO     ==> ?
str[0] `H`;
console.log(str); //Hello(x) hello(o)

// 자바스크립트는 primitive value에 대해 객체처럼 사용하면 primitive value를 감싸는 wrapper object가 생성된다.
// array-like object -> 유사배열객체 - 배열은 아닌데 배열처럼 쓸 수 있는 객체 - 사용이 끝나면 다시 Str-'hello'가 된다.

let myObj = {
    'name' : '홍길동',
    'age' : 20,
    'address' : '서울',
    'myInfo' : function myFunc() {} // 익명함수 anonymous function 
}

console.log(moObj.name); // 홍길동
myObj.myInfo(); // 소리없는 아우성!

// 객체 literal로 객체를 살펴보고 있다.
let obj = {
    'name' : '홍길동',
    10: 200
}
console.log(obj.name); // {name : '홍길동'}
console.log(obj['10']); // 객체 표현방식 2
// {address : '인천' } obj.addrass = '서울'; 이렇게 되면 오류가 안나고 두개의 값이 추가될 뿐이다.

// typeof 라는 연산자 // 값의 데이터 타입을 알려주는 역할
console.log(typeof 100); // number
// Hello (string) // undefined (undefined) //
// true (boolean) // Symbol(symbol) // [1,2,3] (object)
// null (object) 오류지만 지금 못고치는 상황이다.
let myFunc = function /* haha */() {}
//object가 나와야 하는데 function
// 함수선언문
function myfunc1(a, b) {
    return a + b;
} 
console.log(myfunc1(10,20)); // 30
3 * (4 + 5)
//////////////////////////////////
console.log(myFunc1(10,20)); // 30
console.log(myFunc2(10,20)); // error 
// 함수 선언문
function myFunc(a, b) {
    return a + b; }
// 이름을 따서 변수를 만든다. -> function myFunc 가 그대로 저장되는 형식
// Creation phase 에서 이루어진다.
// 함수 표현식
let myFunc = function(a, b) {
    return a + b;
}
// execution phase 에서 이루어진다.
// 자바스크립트에서는 변수가지고 함수를 호출하는 시스템이다.

