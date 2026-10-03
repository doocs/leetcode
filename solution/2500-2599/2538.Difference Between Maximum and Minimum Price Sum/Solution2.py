class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        down = [(0, 0)] * n
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
                a, b = price[i], 0
                for j in g[i]:
                    if j != fa:
                        c, d = down[j]
                        ans = max(ans, a + d, b + c)
                        a = max(a, price[i] + c)
                        b = max(b, price[i] + d)
                down[i] = (a, b)
        return ans
