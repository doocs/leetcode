class Solution:
    def arraySign(self, nums: List[int]) -> int:
        res = 1
        for num in nums:
            if num == 0:
                return 0
            if num < 0:
                res *= -1
        return res
