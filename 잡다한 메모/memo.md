벡터
내적연산, 외적연산?!?!?!



자바에서 람다식(lambda expression)은 함수형 프로그래밍을 지원하기 위한 기능 중 하나입니다. 기존의 자바에서는 메소드를 호출하기 위해 객체를 생성하고, 그 객체를 이용해 메소드를 호출하는 방식으로 처리되었습니다. 하지만 람다식을 이용하면 함수를 변수처럼 다룰 수 있습니다.

람다식은 다음과 같은 구조를 가집니다.

```java
(parameter) -> { body }
// 여기서 parameter는 매개변수를 나타내고, body는 람다식의 구현부분입니다.

// 예를 들어, 다음은 람다식을 이용해 add 메소드를 정의하는 예제입니다.

interface Addable {
    int add(int x, int y);
}

public class Main {
    public static void main(String[] args) {
        Addable add = (x, y) -> x + y;
        System.out.println(add.add(10, 20)); // 30
    }
}
```
위 예제에서 Addable은 함수형 인터페이스입니다. add 메소드는 두 개의 정수를 매개변수로 받아서, 그 합을 반환합니다. add 변수는 람다식으로 구현된 함수를 참조합니다. 람다식에서는 add 메소드의 구현부분이 {} 안에 작성됩니다.

람다식을 이용하면 코드가 간결해지고, 가독성이 좋아집니다. 또한, 람다식을 이용하면 함수형 인터페이스를 구현하는 클래스나 익명 클래스를 작성하지 않아도 되므로, 코드의 양이 줄어들고 유지보수가 쉬워집니다.

```java
public static boolean isPrime(int n) {
    if (n < 2) {
        return false;
    }
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);

    for (int i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return isPrime[n];
}
```