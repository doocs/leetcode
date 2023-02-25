class Solution:
    def maximumDifference(self, nums: List[int]) -> int:
        mi = inf
        ans = -1
        for x in nums:
            if x > mi:
                ans = max(ans, x - mi)
            else:
                mi = x
        return ans
