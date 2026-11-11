class Solution:
    def maximumLength(self, nums: list[int], k: int) -> int:
        f = [1] * len(nums)
        last = {}
        ans = 1
        for i, x in enumerate(nums):
            x %= k
            y = (k - x) % k
            if y in last:
                j = last[y]
                f[i] = max(f[i], f[j] + 1)
                ans = max(ans, f[i])
            last[x] = i
        return ans
