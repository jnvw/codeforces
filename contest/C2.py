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
        p += 1
        
        a = [0] * (n + 1)
        for i in range(1, n + 1):
            a[i] = int(d[p])
            p += 1
            
        k = [1] * (n + 1)
        lk = 1
        for i in range(2, n + 1):
            if a[i] > a[i-1] + 1:
                lk = i
            k[i] = lk
            
        ne = [n + 1] * (n + 1)
        st = []
        for i in range(1, n + 1):
            while st and a[st[-1]] >= a[i]:
                ne[st.pop()] = i
            st.append(i)
            
        adj = [[] for _ in range(n + 2)]
        for i in range(1, n + 1):
            adj[ne[i]].append(i)
            
        tin = [0] * (n + 2)
        tout = [0] * (n + 2)
        tm = 0
        
        st2 = [(n + 1, False)]
        while st2:
            u, f = st2.pop()
            if not f:
                tm += 1
                tin[u] = tm
                st2.append((u, True))
                for v in reversed(adj[u]):
                    st2.append((v, False))
            else:
                tout[u] = tm
                
        sq = [[] for _ in range(n + 1)]
        for i in range(1, n + 1):
            sq[k[i]].append(i)
            
        bit = [0] * (n + 3)
        ans = [0] * (n + 1)
        
        for j in range(1, n + 1):
            x = tin[j]
            while x <= n + 1:
                bit[x] += 1
                x += x & -x
                
            for q in sq[j]:
                r = tout[q]
                sr = 0
                while r > 0:
                    sr += bit[r]
                    r -= r & -r
                    
                l = tin[q] - 1
                sl = 0
                while l > 0:
                    sl += bit[l]
                    l -= l & -l
                    
                ans[q] -= (sr - sl)
                
            r = tout[j]
            sr = 0
            while r > 0:
                sr += bit[r]
                r -= r & -r
                
            l = tin[j] - 1
            sl = 0
            while l > 0:
                sl += bit[l]
                l -= l & -l
                
            ans[j] += (sr - sl)
            
        ts = 0
        for i in range(1, n + 1):
            ok = (tin[i] <= tin[k[i]] <= tout[i])
            t1 = k[i] if ok else 0
            w = t1 + ans[i] - 1
            ts += (n - i + 1) * (1 + w)
            
        o.append(str(ts))
        
    print('\n'.join(o))

if __name__ == '__main__':
    solve()