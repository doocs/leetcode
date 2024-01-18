class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        nums.sort()
        for i in range(0, len(nums), 2):
            nums[i : i + 2] = nums[i : i + 2][::-1]
