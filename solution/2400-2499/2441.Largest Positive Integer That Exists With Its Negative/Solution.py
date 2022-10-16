class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        ans = -1
        s = set(nums)
        for v in s:
            if -v in s:
                ans = max(ans, v)
        return ans
