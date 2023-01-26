function foo() {

}
// 첫번째 phase에서 foo라는 변수가 생성(묵시적으로)
foo.myName = '홍길동'
foo.myFunc = function() {
    console.log(this);
}

console.log(foo.myName); // 홍길동
console.log(foo.myFunc); 
foo.myFunc();