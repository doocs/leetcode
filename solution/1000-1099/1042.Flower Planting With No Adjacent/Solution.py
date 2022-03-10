class Solution:
    def gardenNoAdj(self, n: int, paths: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for x, y in paths:
            x, y = x - 1, y - 1
            g[x].append(y)
            g[y].append(x)
        ans = [0] * n
        for u in range(n):
            colors = set(ans[v] for v in g[u])
            for c in range(1, 5):
                if c not in colors:
                    ans[u] = c
                    break
        return ans
