class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            if nums[p] & 1 == 1:
                p += 1
                continue
            if nums[q] & 1 == 0:
                q -= 1
                continue
            nums[p], nums[q] = nums[q], nums[p]
        return nums
