class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        for num in nums:
            index = abs(num) - 1
            if nums[index] > 0:
                nums[index] *= -1
        res = []
        for i, v in enumerate(nums):
            if v > 0:
                res.append(i + 1)
        return res
