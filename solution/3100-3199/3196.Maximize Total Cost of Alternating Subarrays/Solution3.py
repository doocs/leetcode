class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0, 0] for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(2):
                ans = nums[i] + f[i + 1][1]
                if j == 1:
                    ans = max(ans, -nums[i] + f[i + 1][0])
                f[i][j] = ans
        return f[0][0]
