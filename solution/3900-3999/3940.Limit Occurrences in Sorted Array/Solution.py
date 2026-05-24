class Solution:
    def limitOccurrences(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        cnt = l = 1
        for r in range(1, n):
            if nums[r] != nums[r - 1]:
                cnt = 1
            else:
                cnt += 1
            if cnt <= k:
                nums[l] = nums[r]
                l += 1
        return nums[:l]
