class Solution:
    def countElements(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k == 0:
            return n
        nums.sort()
        return sum(nums[n - k] > nums[i] for i in range(n - k))
