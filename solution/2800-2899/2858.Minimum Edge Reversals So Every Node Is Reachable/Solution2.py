class Solution:
    def minEdgeReversals(self, n: int, edges: List[List[int]]) -> List[int]:
        ans = [0] * n
        g = [[] for _ in range(n)]
        for x, y in edges:
            g[x].append((y, 1))
            g[y].append((x, -1))
        stk = [(0, -1)]
        while stk:
            i, fa = stk.pop()
            for j, k in g[i]:
                if j != fa:
                    if k < 0:
                        ans[0] += 1
                    stk.append((j, i))
        stk = [(0, -1)]
        while stk:
            i, fa = stk.pop()
            for j, k in g[i]:
                if j != fa:
                    ans[j] = ans[i] + k
                    stk.append((j, i))
        return ans
