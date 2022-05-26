class Solution(object):
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """

        if not nums or target < nums[0] or target > nums[-1]:
            return [-1, -1]

        # Finding the lower limit
        left = 0
        right = len(nums)

        while left < right:
            middle = (left + right)/2
            if nums[middle] >= target:
                right = middle

            else:
                left = middle + 1


        low = left
        # Finding the higher limit
        left = 0
        right = len(nums)

        while left < right-1:
            middle = (left + right) / 2
            if nums[middle] > target:
                right = middle
            else:
                left = middle
        high = left

        if nums[low] == target and nums[high] == target:
            return [low, high]
        else:
            return [-1, -1]
