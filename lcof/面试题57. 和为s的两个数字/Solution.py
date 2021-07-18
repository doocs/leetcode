class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            s = nums[p] + nums[q]
            if s == target:
                return [nums[p], nums[q]]
            if s < target:
                p += 1
            else:
                q -= 1
