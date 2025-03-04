class Solution:
    def maximumSumScore(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        ans = -inf
        for x in nums:
            l += x
            ans = max(ans, l, r)
            r -= x
        return ans
