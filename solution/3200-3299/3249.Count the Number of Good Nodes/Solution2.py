class Solution:
    def countGoodNodes(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        sz = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            a, fa, state = stk.pop()
            if state == 0:
                stk.append((a, fa, 1))
                for b in g[a]:
                    if b != fa:
                        stk.append((b, a, 0))
            else:
                pre = -1
                cnt = ok = 1
                for b in g[a]:
                    if b != fa:
                        cur = sz[b]
                        cnt += cur
                        if pre < 0:
                            pre = cur
                        elif pre != cur:
                            ok = 0
                ans += ok
                sz[a] = cnt
        return ans
