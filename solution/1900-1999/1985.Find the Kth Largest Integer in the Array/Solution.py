class Solution:
    def kthLargestNumber(self, nums: list[str], k: int) -> str:
        return nlargest(k, nums, key=lambda x: int(x))[k - 1]
