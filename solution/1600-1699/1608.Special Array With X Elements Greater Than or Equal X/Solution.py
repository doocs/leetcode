class Solution:
    def specialArray(self, nums: List[int]) -> int:
        for x in range(1, len(nums) + 1):
            cnt = sum(v >= x for v in nums)
            if cnt == x:
                return x
        return -1
