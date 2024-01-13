class Solution:
    def specialArray(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        for x in range(1, n + 1):
            cnt = n - bisect_left(nums, x)
            if cnt == x:
                return x
        return -1
