class Solution:
    def specialArray(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        for x in range(n + 1):
            idx = bisect_left(nums, x)
            cnt = n - 1 - idx + 1
            if cnt == x:
                return x
        return -1
