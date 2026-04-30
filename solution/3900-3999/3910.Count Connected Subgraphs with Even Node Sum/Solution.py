class Solution:
    def evenSumSubgraphs(self, nums: list[int], edges: list[list[int]]) -> int:
        n = len(nums)
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        m = (1 << n) - 1
        ans = 0
        for sub in range(1, m + 1):
            s = sum(x for i, x in enumerate(nums) if sub >> i & 1)
            if s % 2:
                continue
            vis = m ^ sub

            def dfs(u: int) -> None:
                nonlocal vis
                vis |= 1 << u
                for v in g[u]:
                    if (vis >> v & 1) == 0:
                        dfs(v)

            dfs(sub.bit_length() - 1)
            if vis == m:
                ans += 1
        return ans
