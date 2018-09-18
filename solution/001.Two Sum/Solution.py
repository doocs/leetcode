class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        for i in nums:
            tmp=nums.copy()
            tmp.remove(i)
            if target-i in tmp:
                return [nums.index(i),nums.index(target-i)]
        return [None,None]

