class Solution:
    def finishTime(self, n: int, edges: List[List[int]], baseTime: List[int]) -> int:
        def dfs(i: int) -> int:
            if not g[i]:
                return baseTime[i]
            earliest, latest = inf, -inf
            for j in g[i]:
                a = dfs(j)
                earliest = min(earliest, a)
                latest = max(latest, a)
            own_duration = (latest - earliest) + baseTime[i]
            return latest + own_duration

        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
        return dfs(0)
