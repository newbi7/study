⑤ 피라미드 만들기

두둥!

삼각형도 어려웠는데 피라미드 등장! 🔥

별부터 차근차근 살펴보면

첫 번째 줄에 별 1개

두 번째 줄에 별 3개

세 번째 줄에 별 5개

...

마지막 줄에 사용자 입력 수 * 2 -1개

즉 2만큼 증가하는 홀수인 것을 알 수 있습니다.

 

공백은

첫 번째 줄에 9개

두 번째 줄에 8개

세 번째 줄에 7개

...

마지막 줄에 0개인 걸로 보아

사용자 입력 수 - i 개 인 것을 알 수 있습니다.

공백 j for문, 별 j for문을 이용해 만들 수 있습니다.

 

1) 공백, 별 바로 출력

for (int i = 1; i <= number; i++) {

    // 공백 j for문
    for (int j = number; j > i; j--) {
        System.out.print(" ");
    }
    // 별 j for문
    for (int j = 1; j <= 2 * i - 1; j++) {
        System.out.print("*");
    }
    // 공백 j for문
    for (int j = number; j > i; j--) {
        System.out.print(" ");
    }
    
    System.out.println("");
    
}
위 코드를 보면 공백 j for문이 2개입니다만 마지막 공백 j for문은 필요 없습니다...

하지만 만약의 경우 오른쪽 공백도 출력을 해야 한다! 하면 입력해주시면 됩니다.

 

  1. i = 1

  1) 공백 j for문

  → j = 10 → j > i → true → 공백 출력 → j-- → j = 9 → j > i → true → 공백 출력 →...

→ j = 1 → j > i → false → 공백 j for문 종료

  2) 별 j for문

  → j = 1 → j <= 2*i -1 → true → * 출력 → j++ → j = 2 → j <= 2*i -1 → false → j for문 종료

→ println(); 실행 → i ++

  2. i = 2

  1) 공백 j for문

  → j = 10 → j > i → true → 공백 출력 → j-- → j = 9 → j > i → true → 공백 출력 →...

→ j = 2 → j > i → false → 공백 j for문 종료

  2) 별 j for문

  → j = 1 → j <= 2*i -1 → true → * 출력 → j ++  → j = 2 → j <= 2*i -1 → true → * 출력 → j++ →

j = 3 → j <= 2*i -1 → true → * 출력 → j ++ → j = 4 → j <= 2*i -1 → false → 별 j for문 종료

→ println(); 실행 →i ++

  .....

  위와 단계 반복

2) String 사용 01

for (int i = 1; i <= number; i++) {
    String stars = "";

    // 공백 j for문
    for (int j = 1; j <= number - i; j++) {
        stars += " ";
    }

    // 별 j for문
    for (int j = 1; j <= 2 * i - 1; j++) {
        stars += "*";
    }

    System.out.println(stars);
    
}
 

3) String 사용 02

for (int i = 1; i <= number; i++) {
    String stars = "";

    // 공백 j for문
    for (int j = i; j < number; j++) {
        stars += " ";
    }

    // 별 j for문
    for (int j = 1; j <= 2 * i - 1; j++) {
        stars += "*";
    }
    
    System.out.println(stars);
}
 

4) 변수 사용

int min = number;
int max = number;

for (int i = 1; i <= number; i++) {
    for (int j = 1; j <= 2 * number - 1; j++) {
        if (j >= min && j <= max) {
            System.out.print("*");
        } else {
            System.out.print(" ");
        }
    }
    System.out.println("");
    min--;
    max++;
}
 

 


⑥ 역 피라미드 만들기

피라미드와 반대로

별 개수가

첫 째 줄에서 2 * 사용자 입력 수 - 1

둘째 줄 2 * 사용자 입력 수 - 1 - 2

...

마지막 줄에서 1개

즉 2 * 사용자 입력 수 - i 개입니다.

 

공백 수는

첫 줄 0개

둘째 줄 1개

세 번째 줄 2개...

i - 1개씩 증가하는 것을 알 수 있습니다.

 

1) j for문

for (int i = 1; i <= number; i++) {
    // 왼쪽 공백 j for 문
    for (int j = 1; j < i; j++) {
        System.out.print(" ");
    }
    
    // 별 j for문
    for (int j = i; j <= 2 * number - i; j++) {
        System.out.print("*");
    }

    // 오른쪽 공백 j for문
    for (int j = 1; j < i; j++) {
        System.out.print(" ");
    }
    
    System.out.println("");

}
2) String 사용 01

for (int i = 1; i <= number; i++) {
    String stars = "";

    // 공백 j for 문
    for (int j = 1; j <= i - 1; j++) {
        stars += " ";
    }

    // 별 j for 문
    for (int j = 1; j <= 2 * number + 1 - 2 * i; j++) {
        stars += "*";
    }

    System.out.println(stars);
}
 

3) String 사용 02

for (int i = number; i >= 1; i--) {
    String stars = "";

    // 공백 j for문
    for (int j = 1; j <= number - i; j++) { 
        stars += " ";
    }

    // 별 j for문
    for (int j = 1; j <= 2 * i - 1; j++) {
        stars += "*";
    }

    System.out.println(stars);
}
 

같은 모양도 다양한 방법으로 출력할 수 있다.

 


⑦ 세로로 긴 삼각형 만들기 01

출력된 줄 수는 사용자 입력 수 * 2 - 1이다.


영역 나누기 예시 01

영역 나누기 예시 02
자세히 보면 별의 개수가 사용자 입력수만큼 출력되는 줄이 있는데,

그 줄을 기준으로 위, 아래로 나눠서 윗부분 for문, 아래 부분 for문으로 출력할 수 있다.

 

1) 윗 삼각형 길게 출력

for(int i = 1; i <= number; i++) {
            
    // 별 j for문
    for(int j = 1; j <= i; j++) {
        System.out.print("*"); 
    }
    
    System.out.println(""); 
}
        
for(int i =1; i <= number-1; i++) {
    
    // 별 j for문      
    for(int j = number-1; j>=i; j-- ) {
        System.out.print("*");         
    }
    
    System.out.println("");
}
첫 번째 i for문과 두 번째 i for문의 가장 큰 차이점은 i 조건식과 j for문의 변화조건식이다.

 

2) 윗 삼각형 길게 출력 & String 사용

for(int i = 1; i <= number; i++) {

    for(int j = 1; j <= i; j++) {
        System.out.print("*");
    }
    System.out.println("");
}
        
for(int i = 2; i <= number; i++) {
            
    String stars = "";
              
    for(int j = i; j <= number; j++) {
        stars += "*";
    }
    System.out.println(stars);     
}
 

 

3) 윗 삼각형 짧게 출력

for(int i = 1; i < number; i++) {

    for(int j = 1; j <= i; j++) {
        System.out.print("*");
    }
    System.out.println("");
}

for(int i = 1; i <= number; i++) {
            
    String stars = "";
              
    for(int j = i; j <= number; j++) {
        stars += "*";
    }
    System.out.println(stars);
}
 


⑧ 세로로 긴 삼각형 만들기 02

7번과 비슷하지만 다른 점은 공백수가 사용자 입력한 수 - 별의 개수 만큼 있어야 한다는 점이다.

 

1) i for문 2개 사용

for(int i = 1; i <= number; i++) {
    String stars = "";
    
    //1. 증가
    for(int j = 1; j <= number - i; j++) {
        stars += " ";
    }
           
    for(int j = 1; j <= i; j++) {
        stars += "*";
    }
    
    System.out.println(stars);          
}
        
for(int i = 1; i < number; i++) {
     String stars = "";
            
    //2. 감소
    for(int j = 1; j <= i; j++) {
        stars += " ";
    }
            
    for(int j = 1; j <= number - i; j++) {
       stars += "*";
    }
    
    System.out.println(stars);
}
 

2) i for문 조건식 범위를 출력되는 줄 수만큼으로 설정

for(int i = 1; i <= 2 * number-1; i++) {
    String stars ="";

    if (i < number) {
        
        // 공백 j for문
        for(int j = 1; j <= number -i; j++) {
            stars += " ";
        }
                
        // 별 j for문
        for(int j = 1; j <= i; j++) {
            stars += "*";
        }
                
    } else {
        int lowerI = i - number + 1;
        
        // 공백 j for문
        for(int j = 1; j <= lowerI-1; j++) {
           stars += " ";
        }
                
        // 별 j for문
        for(int j = lowerI; j <= number; j++) {
            stars += "*";
        }
                
    } //if-else중괄호
    
    System.out.println(stars);
    
} //i for문 중괄호
 

 



⑨ 마름모 만들기

위 삼각형처럼 섹션을 2 개로 나눠서 생각해야 편하다.

출력되는 줄 수는 사용자 입력 수 X 2 -1 줄이다.

별은 가장 많이 출력되는 개수가 사용자 입력 수 X 2 -1개이며 2n-1개씩 증가한다.

 

1) i for문 2개 사용

for(int i = 1; i <= number; i++) {

    // 왼쪽 공백
    for(int j = number; j > i; j--) {
        System.out.print(" ");
    }
    
    // 별 개수
    for(int k = 1; k <= 2*i-1 ; k++) {
        System.out.print("*");
    }
    
    // 오른쪽 공백
    for(int l = number; l > i; l--) {
        System.out.print(" ");
    }
    
    System.out.println("");
}

for(int i = 1; i < number; i++) {

    // 왼쪽 공백
    for(int j = 1; j <= i; j++) {
        System.out.print(" ");
    }
    
    // 별 수
    for(int k = i; k < 2*number - i-1; k++) {
        System.out.print("*");
    }
            
    // 오른쪽 공백
    for(int j = 1; j <= i; j++) {
        System.out.print(" ");
    }
    
    System.out.println("");
}
2) i for문 1개 사용 & 변수 사용

for(int i = 1; i <= 2*number -1; i++) {
    String stars = "";
           
    // i번째 줄의 공백 갯수를 저장할 int 변수
    int spaceWidth = 0;
            
    // i번째 줄의 별 갯수를 저장할 int 변수
    int starWidth = 0;
            
    // if - else 구조
    // spaceWidth와 starWidth의 값을 결정한다.
    if (i < number) {
        
        // 공백 개수 (number-1 ~ 1)
        spaceWidth = number -i;
        // 별 개수
        starWidth = 2*i -1; 
        
    } else { 
               
        int lowerI = 2*number-i;
        // 공백 개수 (0 ~ number-1)
        spaceWidth = number -lowerI;
        // 별 개수 (2*number-1 ~ 1)
        starWidth = 2*lowerI -1;
    }
            
    // spaceWidth번 반복하면서 stars에 공백을 추가하는 j for문
    for(int j = 1; j <= spaceWidth; j++) {
        stars += " ";
    }
            
   // starWidth번 반복하면서 stars에 별을 추가하는 j for문
    for(int j = 1; j <= starWidth; j++) {
        stars += "*";
    }
            
   System.out.println(stars);
}
 

 


⑩ 속 빈 마름모 만들기

3 부분으로 섹션을 나눠서 생각하는 것이 편하다.

제일 첫 줄과 마지막 줄은 공백 없이 모두 별로 출력된다.

개수는 사용자 입력 수 X 2 - 1 개 이므로 줄 수만큼 출력되는 것을 알 수 있다.

그렇다면 if 문을 사용하여 i가 1일 때 와 i가 사용자 입력 수 X 2 - 1 일때만 별이 출력될 수 있도록하고

else if문과 else 문을 사용해 마름모를 2 섹션으로 나눠 윗 부분, 아랫 부분 공백, 별에 해당하는 변수를 만들어

출력될 수 있도록 한다.

 

1) if - else if - else 구조 & 변수 사용

for (int i = 1; i <= 2 * number - 1; i++) {
    String stars = "";

    // 공백 개수 저장 변수
    int spaceWidth = 0;

    // 별 개수 저장 변수
    int starWidth = 0;

    if (i == 1 || i == 2 * number - 1) {
        for (int j = 1; j <= 2 * number - 1; j++) {
            stars += "*";
        }

    } else if (i < number) {
        // 윗부분

        // 윗부분의 변환된 i의 값을 저장할 int 변수
        int upperI = i - 1;
        starWidth = number - upperI;

        // 공백 1, 3, 5...
        spaceWidth = 2 * upperI - 1;

    } else {
        // 아랫부분

        // 아랫부분의 변환된 i의 값을 저장할 int 변수
        int lowerI = i - number + 1;
        starWidth = lowerI;
        spaceWidth = 2 * number - 2 * lowerI - 1;
    }

    // 왼쪽 별 담당 j for문
    for (int j = 1; j <= starWidth; j++) {
        stars += "*";
    }

    // 공백 담당 j for문
    for (int j = 1; j <= spaceWidth; j++) {
        stars += " ";
    }

    // 오른쪽 별 담당 j for문
    for (int j = 1; j <= starWidth; j++) {
        stars += "*";
    }

    System.out.println(stars);
}