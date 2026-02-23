for _ in range(int(input())):
    n = int(input())
    s = input().strip()
    
    need_a = (n + 1) // 2
    need_b = n // 2
    
    count_a = s.count('a')
    count_b = s.count('b')
    count_q = s.count('?')
    
    rem_a = need_a - count_a
    rem_b = need_b - count_b
    
    if rem_a < 0 or rem_b < 0:
        print("NO")
    elif rem_a + rem_b == count_q:
        print("YES")
    else:
        print("NO")