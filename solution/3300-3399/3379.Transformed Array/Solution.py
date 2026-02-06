class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        n = len(nums)
        return [nums[(i + x % n + n) % n] for i, x in enumerate(nums)]
