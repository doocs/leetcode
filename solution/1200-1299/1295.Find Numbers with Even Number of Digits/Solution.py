class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        res = 0
        for num in nums:
            res += (len(str(num)) & 1) == 0
        return res
