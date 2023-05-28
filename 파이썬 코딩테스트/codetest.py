# 문제설명4,13,413,134와같이숫자가13과4를이용해서만들수있는수를불행한수(Unlucky Number)라고정의하겠습니다.그리고불행한수가오름차순으로나열된수열을불행한수열이라고하겠습니다.예를들면불행한수열은다음과같이나열될수있습니다.S={4,13,44,134,413,444,1313….}n이매개변수로주어질때,불행한수열에서n번째불행한수를return하도록solution함수를완성해주세요.제한사항n은5,000이하의자연수입니다. #수열을 실행 할 때 [4,13,44,134,413,444,1313,....] 이 나와야해 
# 다음과같이번식하는드래곤이있습니다.갓낳은드래곤알하나를집으로데려왔을때,n일후엔드래곤과드래곤알이몇개일지알아내려합니다.드래곤알은이틀뒤에부화합니다.부화한드래곤은매일알을하나씩낳습니다.부화한드래곤은네번알을낳은후,더이상알을낳지않습니다.n이매개변수로주어질때,n일후드래곤과드래곤알이몇개있는지return하는solution함수를완성해주세요.제한조건n은45이하인자연수입니다.입출력예n return 6 12입출력예설명일수0 1 2 3 4 5 6알을낳을수없는드래곤수0 0 0 0 0 0 1알을낳을수있는드래곤수0 0 1 1 2 3 4드래곤알수1 1 1 2 3 5 7 0일~1일:드래곤알이하나있습니다.2일:초기드래곤이부화해,알을하나낳았습니다.따라서드래곤수와드래곤알수의합은2입니다.3일:초기드래곤이한번더알을낳았습니다.따라서드래곤수와드래곤알수의합은3입니다.4일:이틀차에낳은드래곤알이부화했습니다.부화한드래곤과초기드래곤이알을하나씩낳았습니다.따라서드래곤수와드래곤알수의합은5입니다.따라서6일후드래곤과드래곤알은총12개가됩니다.

# from collections import deque

# def solution(n):
#     unlucky = deque([4, 13])
#     idx = 0

#     while len(unlucky) < 10000:
#         num = str(unlucky[idx])
#         unlucky.append(int(num + "4"))
#         unlucky.append(int(num + "13"))
#         idx += 1

#     unlucky = sorted(unlucky)
#     return unlucky[n-1]
