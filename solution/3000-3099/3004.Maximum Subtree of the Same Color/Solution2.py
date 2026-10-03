class Solution:
    def maximumSubtreeSize(self, edges: List[List[int]], colors: List[int]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        size = [1] * n
        ok = [False] * n
        ans = 0
        stk = [(0, -1, 0)]
        while stk:
            a, fa, state = stk.pop()
            if state == 0:
                stk.append((a, fa, 1))
                for b in g[a]:
                    if b != fa:
                        stk.append((b, a, 0))
            else:
                good = True
                for b in g[a]:
                    if b != fa:
                        good = good and colors[a] == colors[b] and ok[b]
                        size[a] += size[b]
                if good:
                    ans = max(ans, size[a])
                ok[a] = good
        return ans
