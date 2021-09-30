class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        def make(nums, i, j):
            return str(nums[i]) if i == j else f'{nums[i]}->{nums[j]}'

        i = j = 0
        n = len(nums)
        res = []
        while j < n:
            while j + 1 < n and nums[j] + 1 == nums[j + 1]:
                j += 1
            res.append(make(nums, i, j))
            i = j + 1
            j = i
        return res
