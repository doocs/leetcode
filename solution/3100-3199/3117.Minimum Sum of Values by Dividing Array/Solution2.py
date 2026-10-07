class Solution:
    def minimumValueSum(self, nums: List[int], andValues: List[int]) -> int:
        n, m = len(nums), len(andValues)
        f = {(0, -1): 0}
        for i, x in enumerate(nums):
            g = {}
            for (j, a), cost in f.items():
                if n - i < m - j:
                    continue
                na = a & x
                if na < andValues[j]:
                    continue
                g[j, na] = min(g.get((j, na), inf), cost)
                if na == andValues[j]:
                    t = cost + x
                    if j + 1 == m:
                        if i == n - 1:
                            g[m, -1] = min(g.get((m, -1), inf), t)
                    else:
                        g[j + 1, -1] = min(g.get((j + 1, -1), inf), t)
            f = g
        ans = f.get((m, -1), inf)
        return ans if ans < inf else -1
