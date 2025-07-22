class Solution:
    def countValidSelections(self, nums: List[int]) -> int:
        s = sum(nums)
        ans = l = 0
        for x in nums:
            if x:
                l += x
            elif l * 2 == s:
                ans += 2
            elif abs(l * 2 - s) == 1:
                ans += 1
        return ans
