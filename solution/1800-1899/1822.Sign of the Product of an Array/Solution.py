class Solution:
    def arraySign(self, nums: List[int]) -> int:
        ans = 1
        for v in nums:
            if v == 0:
                return 0
            if v < 0:
                ans *= -1
        return ans
