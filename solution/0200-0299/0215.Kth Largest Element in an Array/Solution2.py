class Solution:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        return nlargest(k, nums)[-1]
