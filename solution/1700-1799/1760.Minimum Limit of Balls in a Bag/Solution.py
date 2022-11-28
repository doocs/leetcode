class Solution:
    def minimumSize(self, nums: List[int], maxOperations: int) -> int:
        def f(x):
            return sum((v - 1) // x for v in nums) <= maxOperations

        return bisect_left(range(1, max(nums) + 1), True, key=f) + 1
