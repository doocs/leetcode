class Solution:
    def maxKDivisibleComponents(
        self, n: int, edges: List[List[int]], values: List[int], k: int
    ) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        sub = [0] * n
        ans = 0
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                s = values[i]
                for j in g[i]:
                    if j != fa:
                        s += sub[j]
                if s % k == 0:
                    ans += 1
                sub[i] = s
        return ans
