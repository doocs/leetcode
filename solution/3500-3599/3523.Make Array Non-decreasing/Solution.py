class Solution:
    def maximumPossibleSize(self, nums: List[int]) -> int:
        ans = mx = 0
        for x in nums:
            if mx <= x:
                ans += 1
                mx = x
        return ans
