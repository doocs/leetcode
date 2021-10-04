class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        nums = list(zip(scores, ages))
        nums.sort(key=lambda x: (x[1], x[0]))
        dp = [num[0] for num in nums]
        res, n = 0, len(ages)
        for i in range(n):
            for j in range(i):
                if nums[j][0] <= nums[i][0]:
                    dp[i] = max(dp[i], dp[j] + nums[i][0])
            res = max(res, dp[i])
        return res
