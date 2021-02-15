class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        res = t = 0
        for num in nums:
            if num == 1:
                t += 1
            else:
                res = max(res, t)
                t = 0
        return max(res, t)
