```java
문자열 my_string이 매개변수로 주어집니다. my_string을 거꾸로 뒤집은 문자열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public String solution(String my_string) {
        String answer = "";

        for(int i=my_string.length()-1; i>=0; i--){
            answer+=my_string.charAt(i);
        }

        return answer;
    }
}
```

```java
정수가 들어 있는 배열 num_list가 매개변수로 주어집니다. num_list의 원소의 순서를 거꾸로 뒤집은 배열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = new int[num_list.length];
        for(int i = 0; i< num_list.length; i++){
            answer[i] = num_list[num_list.length-i-1];
        }
        return answer;
    }
}
```

```java
정수 배열 numbers와 정수 num1, num2가 매개변수로 주어질 때, numbers의 num1번 째 인덱스부터 num2번째 인덱스까지 자른 정수 배열을 return 하도록 solution 함수를 완성해보세요.

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        int[] result = new int[num2 - num1 + 1];
        for (int i = num1; i <= num2; i++) {
            result[i - num1] = numbers[i];
        }
        return result;
    }
}

```

```java
정수 n이 매개변수로 주어질 때, n 이하의 홀수가 오름차순으로 담긴 배열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public int[] solution(int n) {
    int len = (n + 1) / 2;
    int[] result = new int[len];
    int index = 0;
    for (int i = 1; i <= n; i += 2) {
        result[index] = i;
        index++;
    }
    return result;
}
```

```java
순서쌍이란 두 개의 숫자를 순서를 정하여 짝지어 나타낸 쌍으로 (a, b)로 표기합니다. 자연수 n이 매개변수로 주어질 때 두 숫자의 곱이 n인 자연수 순서쌍의 개수를 return하도록 solution 함수를 완성해주세요.


public int solution(int n) {
    // 결과 변수 초기화
    int count = 0;
    // 1부터 sqrt(n)까지 반복하면서 순서쌍 찾기
    for (int i = 1; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            int j = n / i;
            if (i != j) {
                count += 2;
            } else {
                count++;
            }
        }
    }
    // 결과 반환
    return count;
}
위 코드에서, 1부터 sqrt(n)까지 반복하면서 n을 나누어 떨어지게 하는 i 값을 찾습니다. i로 n을 나누어 떨어뜨릴 수 있다면, n / i도 자동으로 나누어 떨어집니다. 이를 이용하여 j 값을 계산합니다.

그리고 i와 j를 이용하여 순서쌍 (i, j)를 만듭니다. i와 j가 같은 경우에는 i가 n의 제곱근인 경우이므로, 결과 변수 count에 1을 더합니다. i와 j가 다른 경우에는 두 가지 순서쌍 (i, j)와 (j, i)가 있기 때문에, 결과 변수 count에 2를 더합니다.

마지막으로, count 값을 반환합니다.
```

```java

정수가 담긴 배열 array와 정수 n이 매개변수로 주어질 때, array에 n이 몇 개 있는 지를 return 하도록 solution 함수를 완성해보세요.

class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        for (int i = 0; i < array.length; i++) {
        if (array[i] == n) {
            answer++;
        }
    }
        return answer;
    }
}
```

```java
머쓱이네 옷가게는 10만 원 이상 사면 5%, 30만 원 이상 사면 10%, 50만 원 이상 사면 20%를 할인해줍니다.
구매한 옷의 가격 price가 주어질 때, 지불해야 할 금액을 return 하도록 solution 함수를 완성해보세요.

class Solution {
    public int solution(int price) {
           // 결과 변수 초기화
    int payment = price;
    // 할인 적용
    if (price >= 500000) {
        payment *= 0.8;
    } else if (price >= 300000) {
        payment *= 0.9;
    } else if (price >= 100000) {
        payment *= 0.95;
    }
    // 결과 반환
    return payment;
    }
}
```

```java
문자열 my_string과 정수 n이 매개변수로 주어질 때, my_string에 들어있는 각 문자를 n만큼 반복한 문자열을 return 하도록 solution 함수를 완성해보세요.

class Solution {
    public String solution(String my_string, int n) {
    // 결과 문자열 초기화
    StringBuilder repeatedString = new StringBuilder();
    // 문자열 순회하면서 n번 반복한 문자열 생성
    for (int i = 0; i < my_string.length(); i++) {
        char c = my_string.charAt(i);
        for (int j = 0; j < n; j++) {
            repeatedString.append(c);
        }
    }
    // 결과 반환
    return repeatedString.toString();
    }
}
```

```java
중앙값은 어떤 주어진 값들을 크기의 순서대로 정렬했을 때 가장 중앙에 위치하는 값을 의미합니다. 예를 들어 1, 2, 7, 10, 11의 중앙값은 7입니다. 정수 배열 array가 매개변수로 주어질 때, 중앙값을 return 하도록 solution 함수를 완성해보세요.

import java.util.Arrays;

class Solution {
    public int solution(int[] array) {
        Arrays.sort(array);
        int mid = array.length / 2;
        return array[mid];
    }
}

````

```java
두 배열이 얼마나 유사한지 확인해보려고 합니다. 문자열 배열 s1과 s2가 주어질 때 같은 원소의 개수를 return하도록 solution 함수를 완성해주세요.

import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int solution(String[] s1, String[] s2) {
        HashSet<String> set1 = new HashSet<>(Arrays.asList(s1));
        HashSet<String> set2 = new HashSet<>(Arrays.asList(s2));
        set1.retainAll(set2);
        return set1.size();
    }
}
```

```java
머쓱이는 추운 날에도 아이스 아메리카노만 마십니다. 아이스 아메리카노는 한잔에 5,500원입니다. 머쓱이가 가지고 있는 돈 money가 매개변수로 주어질 때, 머쓱이가 최대로 마실 수 있는 아메리카노의 잔 수와 남는 돈을 순서대로 담은 배열을 return 하도록 solution 함수를 완성해보세요.

class Solution {
    public int[] solution(int money) {
        int numAmericano = money / 5500;
        int leftMoney = money % 5500;
        return new int[] {numAmericano, leftMoney};
    }
}
```

```java
영어에선 a, e, i, o, u 다섯 가지 알파벳을 모음으로 분류합니다. 문자열 my_string이 매개변수로 주어질 때 모음을 제거한 문자열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public String solution(String my_string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                result.append(c);
            }
        }
        return result.toString();
    }
}
```

```java
정수 배열 numbers가 매개변수로 주어집니다. numbers의 원소 중 두 개를 곱해 만들 수 있는 최댓값을 return하도록 solution 함수를 완성해주세요.

import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers); // 오름차순으로 정렬
        int n = numbers.length;
        int max = numbers[n-1] * numbers[n-2]; // 가장 큰 두 수를 곱한 값으로 초기화
        int product;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                product = numbers[i] * numbers[j];
                if (product > max) {
                    max = product;
                }
            }
        }
        return max;
    }
}
```

```java
정수 배열 numbers가 매개변수로 주어집니다. numbers의 각 원소에 두배한 원소를 가진 배열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length]; // 결과 배열 초기화
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[i] * 2; // 각 원소에 두배한 값을 대입
        }
        return result; // 결과 배열 반환
    }
}
```

```java
문자열 my_string과 문자 letter이 매개변수로 주어집니다. my_string에서 letter를 제거한 문자열을 return하도록 solution 함수를 완성해주세요.

class Solution {
    public String solution(String my_string, String letter) {
        StringBuilder result = new StringBuilder(); // 결과 문자열을 저장할 StringBuilder 객체 생성
        char ch = letter.charAt(0); // 문자열의 첫 번째 문자를 추출하여 char 타입으로 변환
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i); // 문자열의 i번째 문자 추출
            if (c != ch) { // 추출한 문자가 주어진 문자와 다르다면
                result.append(c); // 결과 문자열에 추가
            }
        }
        return result.toString(); // 결과 문자열 반환
    }
}

```

```java
머쓱이는 할머니께 생신 축하 편지를 쓰려고 합니다. 할머니가 보시기 편하도록 글자 한 자 한 자를 가로 2cm 크기로 적으려고 하며, 편지를 가로로만 적을 때, 축하 문구 message를 적기 위해 필요한 편지지의 최소 가로길이를 return 하도록 solution 함수를 완성해주세요.

class Solution {
    public int solution(String message) {
        return message.length()*2;
    }
}
```

```java
선분 세 개로 삼각형을 만들기 위해서는 다음과 같은 조건을 만족해야 합니다.

가장 긴 변의 길이는 다른 두 변의 길이의 합보다 작아야 합니다.
삼각형의 세 변의 길이가 담긴 배열 sides이 매개변수로 주어집니다. 세 변으로 삼각형을 만들 수 있다면 1, 만들 수 없다면 2를 return하도록 solution 함수를 완성해주세요.

import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        // 세 변의 길이를 오름차순으로 정렬
        Arrays.sort(sides);
        
        // 가장 긴 변의 길이
        int longest = sides[2];
        
        // 나머지 두 변의 길이의 합
        int sumOfShorterSides = sides[0] + sides[1];
        
        // 가장 긴 변의 길이가 나머지 두 변의 길이의 합보다 작으면 삼각형을 만들 수 있음
        if (longest < sumOfShorterSides) {
            return 1;
        } else {
            return 2;
        }
    }
}
```
```java
사분면은 한 평면을 x축과 y축을 기준으로 나눈 네 부분입니다. 사분면은 아래와 같이 1부터 4까지 번호를매깁니다.
스크린샷 2022-07-07 오후 3.27.04 복사본.png

x 좌표와 y 좌표가 모두 양수이면 제1사분면에 속합니다.
x 좌표가 음수, y 좌표가 양수이면 제2사분면에 속합니다.
x 좌표와 y 좌표가 모두 음수이면 제3사분면에 속합니다.
x 좌표가 양수, y 좌표가 음수이면 제4사분면에 속합니다.
x 좌표 (x, y)를 차례대로 담은 정수 배열 dot이 매개변수로 주어집니다. 좌표 dot이 사분면 중 어디에 속하는지 1, 2, 3, 4 중 하나를 return 하도록 solution 함수를 완성해주세요.

class Solution {
    public int solution(int[] dot) {
        int x = dot[0];
        int y = dot[1];
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else {
            return 4;
        }
    }
}
```

```java
정수가 담긴 리스트 num_list가 주어질 때, num_list의 원소 중 짝수와 홀수의 개수를 담은 배열을 return 하도록 solution 함수를 완성해보세요.

public int[] solution(int[] num_list) {
    int evenCount = 0;  // 짝수 개수를 담을 변수 초기화
    int oddCount = 0;  // 홀수 개수를 담을 변수 초기화
    
    // num_list를 반복하며 짝수와 홀수의 개수를 센다.
    for (int num : num_list) {
        if (num % 2 == 0) {  // 짝수인 경우
            evenCount++;
        } else {  // 홀수인 경우
            oddCount++;
        }
    }
    
    // 짝수와 홀수의 개수를 담은 배열을 리턴한다.
    return new int[] {evenCount, oddCount};
}

```

```java
머쓱이는 학교에서 키 순으로 줄을 설 때 몇 번째로 서야 하는지 궁금해졌습니다. 머쓱이네 반 친구들의 키가 담긴 정수 배열 array와 머쓱이의 키 height가 매개변수로 주어질 때, 머쓱이보다 키 큰 사람 수를 return 하도록 solution 함수를 완성해보세요.

public int solution(int[] array, int height) {
    int count = 0;  // 머쓱이보다 큰 키를 가진 사람의 수를 담을 변수 초기화
    
    // array를 반복하며 머쓱이보다 큰 키를 가진 사람의 수를 센다.
    for (int num : array) {
        if (num > height) {  // 머쓱이보다 큰 키를 가진 경우
            count++;
        }
    }
    
    // 머쓱이보다 큰 키를 가진 사람의 수를 리턴한다.
    return count;
}

```

```java
문자열 배열 strlist가 매개변수로 주어집니다. strlist 각 원소의 길이를 담은 배열을 retrun하도록 solution 함수를 완성해주세요.

public int[] solution(String[] strlist) {
    int[] lengthArr = new int[strlist.length];  // 결과 배열 생성
    
    // strlist를 반복하며 각 원소의 길이를 구하여 결과 배열에 저장한다.
    for (int i = 0; i < strlist.length; i++) {
        lengthArr[i] = strlist[i].length();
    }
    
    // 각 원소의 길이를 담은 배열을 리턴한다.
    return lengthArr;
}
```

```java
중앙값은 어떤 주어진 값들을 크기의 순서대로 정렬했을 때 가장 중앙에 위치하는 값을 의미합니다. 예를 들어 1, 2, 7, 10, 11의 중앙값은 7입니다. 정수 배열 array가 매개변수로 주어질 때, 중앙값을 return 하도록 solution 함수를 완성해보세요.

import java.util.Arrays;

public int solution(int[] array) {
    Arrays.sort(array);  // 배열을 오름차순으로 정렬한다.
    int midIndex = array.length / 2;  // 중앙 인덱스 계산
    
    if (array.length % 2 == 0) {  // 배열의 길이가 짝수인 경우
        return (array[midIndex - 1] + array[midIndex]) / 2;  // 중앙값 계산하여 리턴
    } else {  // 배열의 길이가 홀수인 경우
        return array[midIndex];  // 중앙값 리턴
    }
}

```

```java
두 배열이 얼마나 유사한지 확인해보려고 합니다. 문자열 배열 s1과 s2가 주어질 때 같은 원소의 개수를 return하도록 solution 함수를 완성해주세요.

import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int solution(String[] s1, String[] s2) {
        HashSet<String> set1 = new HashSet<>(Arrays.asList(s1));
        HashSet<String> set2 = new HashSet<>(Arrays.asList(s2));
        set1.retainAll(set2);
        return set1.size();
    }
}

```

```java

정수 n이 매개변수로 주어질 때 n의 각 자리 숫자의 합을 return하도록 solution 함수를 완성해주세요


public int solution(int n) {
    int sum = 0;
    
    while (n > 0) {
        sum += n % 10;  // 일의 자리 숫자를 구하여 sum에 더합니다.
        n /= 10;  // n을 10으로 나눠 다음 자리 숫자를 구합니다.
    }
    
    return sum;
}

```

```java

문자열 my_string이 매개변수로 주어집니다. my_string안의 모든 자연수들의 합을 return하도록 solution 함수를 완성해주세요.

    public int solution(String my_string) {
        int answer = 0;
        String str = my_string.replaceAll("[^0-9]","");

        for(char ch : str.toCharArray()) {
            answer += Character.getNumericValue(ch);
        }

        return answer;
    }
```

```java
문자열 str1, str2가 매개변수로 주어집니다. str1 안에 str2가 있다면 1을 없다면 2를 return하도록 solution 함수를 완성해주세요.

class Solution {
    public int solution(String str1, String str2) {
        if (str1.contains(str2)) { // str1 안에 str2가 포함되어 있는 경우
            return 1;
        } else { // str1 안에 str2가 포함되어 있지 않은 경우
            return 2;
        }
    }
}

```

```java
개미 군단이 사냥을 나가려고 합니다. 개미군단은 사냥감의 체력에 딱 맞는 병력을 데리고 나가려고 합니다. 장군개미는 5의 공격력을, 병정개미는 3의 공격력을 일개미는 1의 공격력을 가지고 있습니다. 예를 들어 체력 23의 여치를 사냥하려고 할 때, 일개미 23마리를 데리고 가도 되지만, 장군개미 네 마리와 병정개미 한 마리를 데리고 간다면 더 적은 병력으로 사냥할 수 있습니다. 사냥감의 체력 hp가 매개변수로 주어질 때, 사냥감의 체력에 딱 맞게 최소한의 병력을 구성하려면 몇 마리의 개미가 필요한지를 return하도록 solution 함수를 완성해주세요.


class Solution {
    public int solution(int hp) {
        int answer=hp/5;            //필요한 장군개미의 수를 먼저 구함
        hp %= 5;                    //장군개미를 전부 상대하고 남은 hp

        answer+=hp/3;               //남은 hp에 필요한 병정개미의 수를 구함
        hp %= 3;                    //병정개미를 전부 상대하고 남은 hp

        answer+=hp/1;               //남은 hp에 필요한 일개미의 수를 구함

        return answer;
    }
}
```

```java
어떤 자연수를 제곱했을 때 나오는 정수를 제곱수라고 합니다. 정수 n이 매개변수로 주어질 때, n이 제곱수라면 1을 아니라면 2를 return하도록 solution 함수를 완성해주세요.


class Solution {
    public int solution(int n) {
        int sqrt = (int) Math.sqrt(n); // n의 제곱근을 계산하여 정수형으로 변환
        if (sqrt * sqrt == n) { // 정수의 제곱이 n이라면
            return 1;
        } else { // 그렇지 않으면
            return 2;
        }
    }
}

```

```java
가위는 2 바위는 0 보는 5로 표현합니다. 가위 바위 보를 내는 순서대로 나타낸 문자열 rsp가 매개변수로 주어질 때, rsp에 저장된 가위 바위 보를 모두 이기는 경우를 순서대로 나타낸 문자열을 return하도록 solution 함수를 완성해보세요.

class Solution {
    public String solution(String rsp) {
        String answer = "";
        for (int i = 0; i < rsp.length(); i++) {
            char c = rsp.charAt(i);
            if (c == '2') answer += '0'; // 가위(2)는 바위(0)를 이김
            else if (c == '0') answer += '5'; // 바위(0)는 보(5)를 이김
            else answer += '2'; // 보(5)는 가위(2)를 이김
        }
        return answer;
    }
}

```

```java
군 전략가 머쓱이는 전쟁 중 적군이 다음과 같은 암호 체계를 사용한다는 것을 알아냈습니다.

암호화된 문자열 cipher를 주고받습니다.
그 문자열에서 code의 배수 번째 글자만 진짜 암호입니다.
문자열 cipher와 정수 code가 매개변수로 주어질 때 해독된 암호 문자열을 return하도록 solution 함수를 완성해주세요.


class Solution {
    public String solution(String cipher, int code) {
        String answer = "";
        for (int i = 0; i < cipher.length(); i++) {
            if ((i + 1) % code == 0) answer += cipher.charAt(i);
        }
        return answer;
    }
}
```