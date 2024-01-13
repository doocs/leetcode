class Solution:
    def countSpecialSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * 3 for _ in range(n)]
        f[0][0] = nums[0] == 0
        for i in range(1, n):
            if nums[i] == 0:
                f[i][0] = (2 * f[i - 1][0] + 1) % mod
                f[i][1] = f[i - 1][1]
                f[i][2] = f[i - 1][2]
            elif nums[i] == 1:
                f[i][0] = f[i - 1][0]
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1]) % mod
                f[i][2] = f[i - 1][2]
            else:
                f[i][0] = f[i - 1][0]
                f[i][1] = f[i - 1][1]
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2]) % mod
        return f[n - 1][2]
