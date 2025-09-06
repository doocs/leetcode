class Solution:
    def maxSubarraySum(self, nums: List[int]) -> int:
        max_sum = nums[0]
        min_sum = curr_sum = acc_min = 0
        dp = defaultdict(int)
        for num in nums:
            curr_sum += num
            curr = curr_sum - acc_min
            if max_sum < curr:
                max_sum = curr
            if num >= 0:
                continue
            if dp[num] > min_sum:
                dp[num] = min_sum
            dp[num] += num
            if acc_min > dp[num]:
                acc_min = dp[num]
            if min_sum > curr_sum:
                min_sum = curr_sum
        return max_sum
