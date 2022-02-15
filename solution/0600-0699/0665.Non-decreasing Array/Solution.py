class Solution:
    def checkPossibility(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums) < 2:
            return True
        count = 0
        for i in range(1, len(nums)):
            if nums[i] < nums[i - 1]:
                if count == 1:
                    return False
                if not (
                    i + 1 == len(nums)
                    or nums[i + 1] >= nums[i - 1]
                    or i - 2 < 0
                    or nums[i - 2] < nums[i]
                ):
                    return False
                else:
                    count = 1
        return True
