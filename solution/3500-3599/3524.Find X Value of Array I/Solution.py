class Solution:
    def resultArray(self, nums: list[int], k: int) -> list[int]:
        ans = [0] * k
        f = [0] * k
        for x in nums:
            g = [0] * k
            for r, cnt in enumerate(f):
                g[r * x % k] += cnt
            g[x % k] += 1
            for r, cnt in enumerate(g):
                ans[r] += cnt
            f = g
        return ans
