class Solution:
    def canBeIncreasing(self, nums: List[int]) -> bool:
        def check(k: int) -> bool:
            pre = -inf
            for i, x in enumerate(nums):
                if i == k:
                    continue
                if pre >= x:
                    return False
                pre = x
            return True

        i = 0
        while i + 1 < len(nums) and nums[i] < nums[i + 1]:
            i += 1
        return check(i) or check(i + 1)
