class Solution:
    def findMaximumScore(self, nums: List[int]) -> int:
        ans = mx = 0
        for x in nums[:-1]:
            mx = max(mx, x)
            ans += mx
        return ans
