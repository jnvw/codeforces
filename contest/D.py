import sys

def solve():
    d = sys.stdin.read().split()
    if not d:
        return
    
    t = int(d[0])
    p = 1
    o = []
    
    for _ in range(t):
        n = int(d[p])
        k = int(d[p + 1])
        p += 2
        
        if k < n or k > 2 * n - 1:
            o.append("NO")
            continue
            
        o.append("YES")
        
        m = k - n + 1
        a = []
        
        if m == 1:
            a.extend([1, 1])
        else:
            a.extend([1, 2])
            for i in range(2, m):
                a.extend([i + 1, i - 1])
            a.extend([m - 1, m])
            
        for i in range(m + 1, n + 1):
            a.extend([i, i])
            
        o.append(" ".join(map(str, a)))
        
    sys.stdout.write("\n".join(o) + "\n")

if __name__ == '__main__':
    solve()