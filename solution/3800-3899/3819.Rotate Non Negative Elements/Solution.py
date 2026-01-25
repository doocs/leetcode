class Solution:
    def rotateElements(self, nums: List[int], k: int) -> List[int]:
        t = [x for x in nums if x >= 0]
        m = len(t)
        d = [0] * m
        for i, x in enumerate(t):
            d[((i - k) % m + m) % m] = x
        j = 0
        for i, x in enumerate(nums):
            if x >= 0:
                nums[i] = d[j]
                j += 1
        return nums
