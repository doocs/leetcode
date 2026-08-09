class Solution:
    def weightedSum(self, parent: list[int], nums: list[int]) -> int:
        n = len(nums)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        ans = 0
        q = [0]
        d = 0
        while q:
            d += 1
            nq = []
            for i in q:
                ans += nums[i] * (1 - d)
                nq.extend(g[i])
            q = nq
        ans += d * sum(nums)
        return ans
