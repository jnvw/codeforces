for _ in range(int(input())):
    x, y = map(int, input().split())
    
    k = x - 2*y
    
    if k < 0 or k % 3 != 0:
        print("NO")
        continue
    
    k //= 3
    
    if k >= 2 * max(0, -y):
        print("YES")
    else:
        print("NO")