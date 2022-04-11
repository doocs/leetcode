class Solution:
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        nums = list(zip(ages, scores))
        nums.sort()
        n = len(nums)
        dp = [num[1] for num in nums]
        for i in range(n):
            for j in range(i):
                if nums[i][1] >= nums[j][1]:
                    dp[i] = max(dp[i], dp[j] + nums[i][1])
        return max(dp)
