class Solution:
    def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
        g = defaultdict(set)
        for a, b in roads:
            g[a].add(b)
            g[b].add(a)
        ans = 0
        for a in range(n):
            for b in range(a + 1, n):
                if (t := len(g[a]) + len(g[b]) - (a in g[b])) > ans:
                    ans = t
        return ans
