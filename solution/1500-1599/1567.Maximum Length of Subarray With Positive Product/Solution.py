class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * n
        g = [0] * n
        f[0] = int(nums[0] > 0)
        g[0] = int(nums[0] < 0)
        ans = f[0]
        for i in range(1, n):
            if nums[i] > 0:
                f[i] = f[i - 1] + 1
                g[i] = 0 if g[i - 1] == 0 else g[i - 1] + 1
            elif nums[i] < 0:
                f[i] = 0 if g[i - 1] == 0 else g[i - 1] + 1
                g[i] = f[i - 1] + 1
            ans = max(ans, f[i])
        return ans
