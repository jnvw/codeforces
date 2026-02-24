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
        s = d[p + 1]
        p += 2
        
        ok = True
        
        if n % 2:
            if s[0] == 'b':
                ok = False
            st = 1
        else:
            st = 0
            
        if ok:
            for i in range(st, n, 2):
                if s[i] == s[i+1] and s[i] != '?':
                    ok = False
                    break
                    
        o.append("YES" if ok else "NO")
        
    print('\n'.join(o))

if __name__ == '__main__':
    solve()