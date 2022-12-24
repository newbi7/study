# 문제  
장기판에서 나의 포(X)가 잡을 수 있는 말(H)의 개수를 구하는 문제로 10개의 테스트로 가장 작은 사각형은 8x8, 큰경우는 100x100까지 있다  
제약사항 : 1. 나의 포는 가로/세로 방향으로 다른 말 하나를 넘어야 잡을 수 있음  
2. 나의 포는 다른포(Y)를 넘거나 잡을 수 없음  
나의 포(X), 다른 포(Y), 말(H), 빈공간(L)


```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JanggiCannon {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int mapSize = 0;
		int[] cannonCoordinate = new int[2];
		Boolean findMyCannon = false;
		int problemN = 1;
		int result = 0;
		int resultH = 0;
		int resultV = 0;
		
		while(true) {
			try {
				mapSize = Integer.parseInt(sc.nextLine());	// 장기판의 크기를 입력 받는다. nextInt를 사용시 return 소거용 nextLine을 한 번 더 써야 하기 대문에 형변환을 사용함.
			} catch (Exception e) {
				break;
			}
			
			// 장기판 맵을 매트릭스 배열로 만든다.
			char[][] map = new char[mapSize][mapSize];
			
			// 장기판에 알을 담는다.
			for (int i = 0; i < mapSize; i++) {
				map[i] = sc.nextLine().replace(" ", "").toCharArray();
				
				// 장기판을 채우다 포가 나오면 위치를 기억한다.
				if (!findMyCannon) {	// 포의 위치를 찾으면 그 문제에서는 더이상 찾는 시도를 하지 않는다.
					for (int j = 0; j < mapSize; j++) {
						if (map[i][j] == 'X') {
							cannonCoordinate[0] = i;
							cannonCoordinate[1] = j;
							findMyCannon = true;
						}
					}
				}
			}
	
			// Step 1. 수평 계산.
			// 내 포("X")가 있는 수평줄의 문자열을 구한다.
			String horizontal = new String(map[cannonCoordinate[0]], 0, mapSize);
						
			// 계산 메소드에 넣는다.
			resultH = calcAttack(horizontal);
			
			// Step 2. 수직 계산.
			// 내 포("X")가 있는 수직줄의 문자열을 구한다.
			StringBuilder sbd = new StringBuilder();
			for (int i = 0; i < mapSize; i++) {
				sbd.append(map[i][cannonCoordinate[1]]);
			}
			String vertical = sbd.toString();

			// 계산 메소드에 넣는다.
			resultV = calcAttack(vertical);

			// Step 3. 결과 산출 및 다음 문제를 위한 초기화.
			result = resultH + resultV;
			
			System.out.printf("%d번째 문제의 답은 : %d \n", problemN, result);
			findMyCannon = false;
			problemN++;
			result = resultH = resultV = 0;
		}
	}
	
	private static int calcAttack(String line) {
		int result = 0;
		
		// Step 1. 상대 캐논("Y")을 기준으로 나눈다.
		String[] target = line.split("Y"); 
		// Step 2. 내 캐논("X")이 포함된 유효한 구간만 가져온다.
		int targetIndex = includingTargetIndex(target);
		// Step 3. 공백("L")을 제거한다.
		String targetList = target[targetIndex].replace("L", "");
		// Step 4. 계산을 위해 내 캐논("X")을 기준으로 2개의 구간으로 나눈다.
		String[] splitedTargetList = targetList.split("X");
		
		int target1 = 0;
		int target2 = 0;
		// 배열이 존재하지 않을 경우에 대한 예외처리.
		try {
target1 = (splitedTargetList[0].isEmpty()) ? 0 : splitedTargetList[0].length() - 1;	// 배열이 비어있다면 0, 비어있지 않다면 알을 하나 건너 뛰어야 하니 개수 - 1을 리턴.
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			target2 = (splitedTargetList[1].isEmpty()) ? 0 : splitedTargetList[1].length() - 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return target1 + target2;
	}
	
	// 캐논("X")이 포함된 문자열의 인덱스를 찾는 메소드.
	private static int includingTargetIndex(String target[]) {
		int result = 0;
		while (result < target.length) {
			if (target[result].contains("X")) break;
			result++;
		}	
		return result;
	}
}
```
결과 : 

1번째 문제의 답은 : 1 
2번째 문제의 답은 : 1 
3번째 문제의 답은 : 5 
4번째 문제의 답은 : 3 
