class Solution2:
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        Count = [nums.count(color) for color in range(3)]

        color = i = 0

        while i < len(nums):
            for j in range(Count[color]):
                nums[i] = color
                i += 1
            color += 1
