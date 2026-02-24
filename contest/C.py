import sys

def solve():
    d = sys.stdin.read().split()
    if not d:
        return
    
    t = int(d[0])
    i = 1
    o = []
    
    for _ in range(t):
        n = int(d[i])
        i += 1
        
        a = []
        for _ in range(n):
            a.append(int(d[i]))
            i += 1
            
        b = 1
        r = a[0]
        
        for j in range(1, n):
            if a[j] > a[j-1] + 1 or a[j] <= r:
                b += 1
                r = a[j]
                
        o.append(str(b))
        
    print('\n'.join(o))

if __name__ == '__main__':
    solve()