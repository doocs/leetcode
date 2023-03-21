class Solution:
    def binarySearchableNumbers(self, nums: List[int]) -> int:
        n = len(nums)
        ok = [1] * n
        mx, mi = -1000000, 1000000
        for i, x in enumerate(nums):
            if x < mx:
                ok[i] = 0
            else:
                mx = x
        for i in range(n - 1, -1, -1):
            if nums[i] > mi:
                ok[i] = 0
            else:
                mi = nums[i]
        return sum(ok)
