class Solution:
    def missingMultiple(self, nums: list[int], k: int) -> int:
        s = set(nums)
        for i in count(1):
            x = k * i
            if x not in s:
                return x
