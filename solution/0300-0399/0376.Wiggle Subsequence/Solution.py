class Solution:
    def wiggleMaxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 1
        f = [1] * n
        g = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    f[i] = max(f[i], g[j] + 1)
                elif nums[j] > nums[i]:
                    g[i] = max(g[i], f[j] + 1)
            ans = max(ans, f[i], g[i])
        return ans
