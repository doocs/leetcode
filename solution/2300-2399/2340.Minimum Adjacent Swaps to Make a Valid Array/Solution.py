class Solution:
    def minimumSwaps(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        i, j = -1, -1
        for k, v in enumerate(nums):
            if v == mi and i == -1:
                i = k
            if v == mx:
                j = k
        if i == j:
            return 0
        n = len(nums)
        if i < j:
            return i + n - 1 - j
        return i + n - 2 - j
