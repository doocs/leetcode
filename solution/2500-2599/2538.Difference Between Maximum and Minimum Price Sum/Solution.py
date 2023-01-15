class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        def dfs(i, fa):
            a, b = price[i], 0
            for j in g[i]:
                if j != fa:
                    c, d = dfs(j, i)
                    nonlocal ans
                    ans = max(ans, a + d, b + c)
                    a = max(a, price[i] + c)
                    b = max(b, price[i] + d)
            return a, b

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
