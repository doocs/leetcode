class Solution:
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        res=0
        for i in nums:
            res = res^i
        return res