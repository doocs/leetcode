class Solution:
    def longestPath(self, parent: List[int], s: str) -> int:
        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        down = [0] * n
        ans = 0
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j in g[i]:
                    stk.append((j, 0))
            else:
                mx = 0
                for j in g[i]:
                    x = down[j] + 1
                    if s[i] != s[j]:
                        ans = max(ans, mx + x)
                        mx = max(mx, x)
                down[i] = mx
        return ans + 1
