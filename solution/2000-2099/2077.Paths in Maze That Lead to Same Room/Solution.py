class Solution:
    def numberOfPaths(self, n: int, corridors: List[List[int]]) -> int:
        g = defaultdict(set)
        for a, b in corridors:
            g[a].add(b)
            g[b].add(a)
        ans = 0
        for i in range(1, n + 1):
            for j, k in combinations(g[i], 2):
                if j in g[k]:
                    ans += 1
        return ans // 3
