class Solution:
    def smallestIndex(self, nums: List[int]) -> int:
        for idx, val in enumerate(nums):
            sum_val = sum(list(map(int, str(val))))

            if idx == sum_val:
                return idx
        else:
            return -1
