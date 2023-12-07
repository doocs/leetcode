class Solution:
    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        def dfs(a: int, fa: int) -> int:
            nonlocal ans
            sz = 1
            for b in g[a]:
                if b != fa:
                    t = dfs(b, a)
                    ans += ceil(t / seats)
                    sz += t
            return sz

        g = defaultdict(list)
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
