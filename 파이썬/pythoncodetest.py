def solution(want, number, discount):
    fltmxm = []
    count = 0
    total_days = 0
    a = 0
    b = 0
    xmfn = [False]*len(discount)

    for i, j in zip(want, number):
        while a<j:
            fltmxm.append(i)
            a+=1
        a=0
    a=0
    
    while a < len(discount):
        for i in fltmxm:
            while i == discount[b]:
                xmfn[b] = True
                fltmxm.pop(i.index())
                b+=1
                print("1")
            else:
                print("2")
                b+=1
            
        a+=1
    print(xmfn)
    print(fltmxm)
    # for i in discount:
    # total_days+=1
    
    return total_days
