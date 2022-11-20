class Solution:
    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        def dfs(a, fa):
            size = 1
            for b in g[a]:
                if b != fa:
                    t = dfs(b, a)
                    nonlocal ans
                    ans += (t + seats - 1) // seats
                    size += t
            return size

        g = defaultdict(list)
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
