class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        s = {v for v in nums if v}
        return len(s)
