def solution(n):
    answer = 0
    n = 15
    not_dragons = [0, 0, 0, 0, 0, 0, 1]
    dragon_eggs = [1, 1, 1, 2, 3, 5, 7]
    can_dragons = [0, 0, 1, 1, 2, 3, 4]
    if n >= 7:
        for i in range(7, n + 1):
            not_dragons.append(not_dragons[i - 1] + can_dragons[i - 4])
            can_dragons.append(
                can_dragons[i - 1] + dragon_eggs[i - 2] - can_dragons[i - 4]
            )
            dragon_eggs.append(can_dragons[i] + can_dragons[i - 1])
    print(not_dragons)
    print(can_dragons)
    print(dragon_eggs)

    answer = not_dragons[n] + can_dragons[n] + dragon_eggs[n]
    return answer

