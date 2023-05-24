```python
베스트앨범
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
입출력 예
genres	plays	return
["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]


from collections import Counter

def solution(genres, plays):
    answer = []
    songs = {}
    genre_total_plays = {}

    for i in range(len(genres)):
        genre = genres[i]
        play = plays[i]
        
        if genre not in songs:
            songs[genre] = [(i, play)]
            genre_total_plays[genre] = play
        else:
            songs[genre].append((i, play))
            genre_total_plays[genre] += play

    sorted_genres = sorted(genre_total_plays, key=lambda x: genre_total_plays[x], reverse=True)

    for genre in sorted_genres:
        songs[genre].sort(key=lambda x: (x[1], -x[0]), reverse=True)
        selected_songs = songs[genre][:2]
        for song in selected_songs:
            answer.append(song[0])

    return answer

```

```python
스택/큐

다리를 지나는 트럭
문제 설명
트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
0	[]	[]	[7,4,5,6]
1~2	[]	[7]	[4,5,6]
3	[7]	[4]	[5,6]
4	[7]	[4,5]	[6]
5	[7,4]	[5]	[6]
6~7	[7,4,5]	[6]	[]
8	[7,4,5,6]	[]	[]
따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건
bridge_length는 1 이상 10,000 이하입니다.
weight는 1 이상 10,000 이하입니다.
truck_weights의 길이는 1 이상 10,000 이하입니다.
모든 트럭의 무게는 1 이상 weight 이하입니다.

from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0
    trucks_on_bridge = deque()
    trucks_waiting = deque(truck_weights)
    current_weight = 0
    
    while trucks_waiting or trucks_on_bridge:
        answer += 1
        
        if trucks_on_bridge:
            time, truck_weight = trucks_on_bridge[0]
            if answer - time >= bridge_length:
                current_weight -= truck_weight
                trucks_on_bridge.popleft()

        if trucks_waiting:
            if current_weight + trucks_waiting[0] <= weight:
                truck_weight = trucks_waiting.popleft()
                current_weight += truck_weight
                trucks_on_bridge.append((answer, truck_weight))

    return answer

```

```python

주식가격
문제 설명
초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

제한사항
prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
prices의 길이는 2 이상 100,000 이하입니다.
입출력 예
prices	return
[1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
입출력 예 설명
1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
※ 공지 - 2019년 2월 28일 지문이 리뉴얼되었습니다.

def solution(prices):
    n = len(prices)
    answer = [0] * n
    stack = []

    for i in range(n):
        while stack and prices[i] < prices[stack[-1]]:
            j = stack.pop()
            answer[j] = i - j
        stack.append(i)

    while stack:
        j = stack.pop()
        answer[j] = n - j - 1

    return answer

```

```python
더 맵게
문제 설명
매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

제한 사항
scoville의 길이는 2 이상 1,000,000 이하입니다.
K는 0 이상 1,000,000,000 이하입니다.
scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
입출력 예
scoville	K	return
[1, 2, 3, 9, 10, 12]	7	2

import heapq

def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville) 
    
    while scoville[0] < K:
        if len(scoville) < 2:
            answer = -1
            break

        min1 = heapq.heappop(scoville)
        min2 = heapq.heappop(scoville) 
        new_scoville = min1 + (min2 * 2) 
        heapq.heappush(scoville, new_scoville)
        answer += 1

    return answer

```

```python
디스크 컨트롤러
문제 설명
하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다. 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다. 가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것입니다.

예를들어

- 0ms 시점에 3ms가 소요되는 A작업 요청
- 1ms 시점에 9ms가 소요되는 B작업 요청
- 2ms 시점에 6ms가 소요되는 C작업 요청
와 같은 요청이 들어왔습니다. 이를 그림으로 표현하면 아래와 같습니다.
Screen Shot 2018-09-13 at 6.34.58 PM.png

한 번에 하나의 요청만을 수행할 수 있기 때문에 각각의 작업을 요청받은 순서대로 처리하면 다음과 같이 처리 됩니다.
Screen Shot 2018-09-13 at 6.38.52 PM.png

- A: 3ms 시점에 작업 완료 (요청에서 종료까지 : 3ms)
- B: 1ms부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료(요청에서 종료까지 : 11ms)
- C: 2ms부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 16ms)
이 때 각 작업의 요청부터 종료까지 걸린 시간의 평균은 10ms(= (3 + 11 + 16) / 3)가 됩니다.

하지만 A → C → B 순서대로 처리하면
Screen Shot 2018-09-13 at 6.41.42 PM.png

- A: 3ms 시점에 작업 완료(요청에서 종료까지 : 3ms)
- C: 2ms부터 대기하다가, 3ms 시점에 작업을 시작해서 9ms 시점에 작업 완료(요청에서 종료까지 : 7ms)
- B: 1ms부터 대기하다가, 9ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 17ms)
이렇게 A → C → B의 순서로 처리하면 각 작업의 요청부터 종료까지 걸린 시간의 평균은 9ms(= (3 + 7 + 17) / 3)가 됩니다.

각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

제한 사항
jobs의 길이는 1 이상 500 이하입니다.
jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.

import heapq

def solution(jobs):
    total_time = 0 
    elapsed_time = 0  
    job_count = len(jobs) 

    jobs.sort() 

    waiting_jobs = [] 

    while jobs or waiting_jobs:
        while jobs and jobs[0][0] <= elapsed_time:
            start_time, job_time = jobs.pop(0)
            heapq.heappush(waiting_jobs, (job_time, start_time))

        if waiting_jobs:
            job_time, start_time = heapq.heappop(waiting_jobs)
            elapsed_time += job_time
            total_time += elapsed_time - start_time
        else:
            elapsed_time += 1

    answer = total_time // job_count
    return answer

```

```python
가장 큰 수
문제 설명
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.

def solution(numbers):
    numbers = list(map(str, numbers))

    numbers.sort(key=lambda x: (x * 4)[:4], reverse=True)
    answer = ''.join(numbers)

    if answer[0] == '0':
        return '0'
    else:
        return answer

```

```python
전력망을 둘로 나누기
문제 설명
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 2 이상 100 이하인 자연수입니다.
wires는 길이가 n-1인 정수형 2차원 배열입니다.
wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
1 ≤ v1 < v2 ≤ n 입니다.
전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

def solution(n, wires):
    # 그래프 생성
    graph = [[] for _ in range(n + 1)]
    for wire in wires:
        v1, v2 = wire
        graph[v1].append(v2)
        graph[v2].append(v1)
    
    min_diff = float('inf')  # 최소 차이값 초기화
    
    def dfs(v, parent):
        nonlocal min_diff
        
        # 서브 그래프의 송전탑 개수 계산
        count = 1
        for neighbor in graph[v]:
            if neighbor != parent:
                count += dfs(neighbor, v)
        
        # 송전탑 개수의 차이 갱신
        diff = abs(n - 2 * count)
        min_diff = min(min_diff, diff)
        
        return count
    
    dfs(1, 0)  # DFS 탐색 시작
    
    return min_diff

```

```python
모음 사전
문제 설명
사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.

단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.

제한사항
word의 길이는 1 이상 5 이하입니다.
word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
입출력 예
word	result
"AAAAE"	6
"AAAE"	10
"I"	1563
"EIO"	1189
입출력 예 설명
입출력 예 #1

사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다. "AAAAE"는 사전에서 6번째 단어입니다.

입출력 예 #2

"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.

입출력 예 #3

"I"는 1563번째 단어입니다.

입출력 예 #4

"EIO"는 1189번째 단어입니다.

def solution(word):
    vowels = ['A', 'E', 'I', 'O', 'U']  # 알파벳 모음
    word_dict = {}  # 사전을 저장할 딕셔너리
    count = 0  # 단어의 순서 카운트
    
    def generate_words(curr_word):
        nonlocal count
        
        # 단어를 사전에 추가하고 카운트 증가
        word_dict[curr_word] = count
        count += 1
        
        # 단어의 길이가 5 이하일 때, 다음 글자를 추가하여 단어 생성
        if len(curr_word) < 5:
            for vowel in vowels:
                generate_words(curr_word + vowel)
    
    generate_words('')  # 빈 문자열부터 시작
    return word_dict[word]  # 주어진 단어의 순서 반환
```

```python
조이스틱
문제 설명
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

def solution(name):
    answer = 0
    n = len(name)

    def alphabet_to_num(char):
        num_char = [i for i in range(14)] + [j for j in range(12, 0, -1)]
        return num_char[ord(char) - ord('A')]

    for ch in name:
        answer += alphabet_to_num(ch)

    move = n - 1
    for idx in range(n):
        next_idx = idx + 1
        while (next_idx < n) and (name[next_idx] == 'A'):
            next_idx += 1
        distance = min(idx, n - next_idx)
        move = min(move, idx + n - next_idx + distance)

    answer += move
    return answer

# alphabet_to_num(char) 함수:

# 알파벳 문자를 숫자로 변환하는 함수입니다.
# 알파벳 'A'부터 'M'까지는 0부터 13까지의 숫자로 변환하고, 알파벳 'N'부터 'Z'까지는 12부터 1까지의 숫자로 변환합니다.
# 이는 알파벳 조작 시 위/아래로 조작하는 횟수를 나타내기 위한 변환입니다.
# for ch in name:

# 주어진 이름의 각 문자에 대해 반복합니다.
# alphabet_to_num 함수를 통해 각 문자를 숫자로 변환하고, answer에 더합니다.
# 이를 통해 이름을 완성하기 위해 필요한 위/아래 조작 횟수를 계산합니다.
# move = n - 1:

# move 변수를 초기화합니다. 이는 이름을 완성하기 위해 필요한 좌/우 이동 횟수를 나타냅니다.
# 초기값으로 n - 1을 설정하는 이유는 첫 글자는 조작하지 않아도 되기 때문입니다.
# for idx in range(n):

# 0부터 n-1까지의 인덱스에 대해 반복합니다. 각 인덱스는 현재 조작할 문자의 위치를 나타냅니다.
# next_idx = idx + 1:

# 다음 인덱스를 idx의 다음 위치로 설정합니다.
# while (next_idx < n) and (name[next_idx] == 'A'):

# 다음 인덱스가 이름의 범위 내에 있고, 해당 인덱스의 문자가 'A'인 동안 반복합니다.
# 연속된 'A' 문자의 개수를 확인하기 위한 과정입니다.
# distance = min(idx, n - next_idx):

# 현재 위치(idx)와 나머지 이름의 끝과의 거리 중 작은 값을 선택합니다.
# 이는 현재 위치에서 역으로 이동하여 이름의 끝까지 이동하는 경우를 고려하기 위한 값입니다.
# move = min(move, idx + n - next_idx + distance):

# move 값과 현재까지 계산된 좌/우 이동 횟수(idx + n - next_idx + distance) 중 작은 값을 선택하여 업데이트합니다.
# answer += move:

# answer에 좌/우 이동 횟수(move)를 더합니다.
# 이를 통해 이름을 완성하기 위해 필요한 좌/우 조작 횟수를 계산합니다.
# 최종적으로 answer를 반환합니다.
```

```python

큰 수 만들기
문제 설명
어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
k는 1 이상 number의 자릿수 미만인 자연수입니다.

def solution(number, k):
    stack = [number[0]]  # 첫 번째 숫자를 스택에 넣어 시작
    for num in number[1:]:  # 두 번째 숫자부터 탐색
        while len(stack) > 0 and stack[-1] < num and k > 0:
            # 스택의 가장 위에 있는 숫자가 현재 숫자보다 작으면 스택에서 제거하고 k 감소
            stack.pop()
            k -= 1
        stack.append(num)  # 현재 숫자를 스택에 추가
    
    # k가 남아있는 경우 남은 k만큼 스택에서 숫자 제거
    if k > 0:
        stack = stack[:-k]
    
    return ''.join(stack)  # 스택에 남은 숫자들을 문자열로 결합하여 반환

```

```python
게임 맵 최단거리
문제 설명
ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다. 따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.

지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다. 다음은 5 x 5 크기의 맵에, 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고, 상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.

위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다. 캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.
아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.

첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.

두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.

위 예시에서는 첫 번째 방법보다 더 빠르게 상대팀 진영에 도착하는 방법은 없으므로, 이 방법이 상대 팀 진영으로 가는 가장 빠른 방법입니다.

만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다. 예를 들어, 다음과 같은 경우에 당신의 캐릭터는 상대 팀 진영에 도착할 수 없습니다.


게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요. 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.

제한사항
maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.

입출력 예
maps	answer
[[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]	11
[[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]	-1

from collections import deque

def solution(maps):
    # 게임 맵의 크기를 구함
    n = len(maps)
    m = len(maps[0])
    
    # 상하좌우 이동을 위한 방향 벡터
    dx = [-1, 1, 0, 0]  # 상, 하, 좌, 우
    dy = [0, 0, -1, 1]
    
    # BFS를 위한 큐와 방문 체크 배열 초기화
    queue = deque()
    visited = [[False] * m for _ in range(n)]
    
    # 시작 지점 (0, 0)을 큐에 넣고 방문 체크
    queue.append((0, 0))
    visited[0][0] = True
    
    # BFS 탐색 시작
    while queue:
        x, y = queue.popleft()
        
        # 현재 위치가 상대 팀 진영인 경우 최단 거리를 반환
        if x == n - 1 and y == m - 1:
            return maps[x][y]
        
        # 상하좌우 인접한 위치를 확인하며 탐색
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            # 게임 맵을 벗어나는 경우 무시
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            
            # 벽이 있는 경우 무시
            if maps[nx][ny] == 0:
                continue
            
            # 방문하지 않은 위치라면 큐에 넣고 방문 체크
            if not visited[nx][ny]:
                queue.append((nx, ny))
                visited[nx][ny] = True
                
                # 이전 위치까지의 최단 거리 + 1을 현재 위치에 저장
                maps[nx][ny] = maps[x][y] + 1
    
    # BFS를 마친 후에도 상대 팀 진영에 도착하지 못한 경우 -1 반환
    return -1

```