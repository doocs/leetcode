class Solution:
    def maxSubarraySum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[-inf] * 4 for _ in range(n + 1)]
        f[0][0] = 0
        ans = -inf
        for i, x in enumerate(nums, 1):
            f[i][0] = max(f[i - 1][0], 0) + x
            f[i][1] = max(f[i - 1][0], f[i - 1][1], 0) + x * k
            f[i][2] = max(f[i - 1][0], f[i - 1][2], 0) + int(x / k)
            f[i][3] = max(f[i - 1][1], f[i - 1][2], f[i - 1][3]) + x
            ans = max(ans, max(f[i]))
        return ans
