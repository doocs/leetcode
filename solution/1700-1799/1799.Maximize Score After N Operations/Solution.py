class Solution:
    def maxScore(self, nums: List[int]) -> int:
        m = len(nums)
        f = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(i + 1, m):
                f[i][j] = gcd(nums[i], nums[j])
        dp = [0] * (1 << m)
        for k in range(1 << m):
            if (cnt := k.bit_count()) % 2 == 0:
                for i in range(m):
                    if k >> i & 1:
                        for j in range(i + 1, m):
                            if k >> j & 1:
                                dp[k] = max(dp[k], dp[k ^ (1 << i) ^ (
                                    1 << j)] + cnt // 2 * f[i][j])
        return dp[-1]
