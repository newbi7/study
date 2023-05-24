def solution(n, costs):
    answer = 0
    costs.sort(key=lambda x:x[2])
    print(costs)
    return answer