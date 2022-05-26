class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        cnt, res, n = 0, 0, len(nums)
        for i in range(1, n):
            if nums[i] != nums[i - 1]:
                cnt += 1
            res += cnt
        return res
