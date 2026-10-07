class Solution:
    def remainingMethods(
        self, n: int, k: int, invocations: List[List[int]]
    ) -> List[int]:
        f = [[] for _ in range(n)]
        g = [[] for _ in range(n)]
        for a, b in invocations:
            f[a].append(b)
            f[b].append(a)
            g[a].append(b)
        suspicious = [False] * n
        suspicious[k] = True
        stk = [k]
        while stk:
            i = stk.pop()
            for j in g[i]:
                if not suspicious[j]:
                    suspicious[j] = True
                    stk.append(j)
        vis = [False] * n
        for i in range(n):
            if suspicious[i] or vis[i]:
                continue
            vis[i] = True
            stk = [i]
            while stk:
                u = stk.pop()
                for j in f[u]:
                    if not vis[j]:
                        suspicious[j] = False
                        vis[j] = True
                        stk.append(j)
        return [i for i in range(n) if not suspicious[i]]
