class Solution:
    def maximumValueSum(self, nums: List[int], k: int, edges: List[List[int]]) -> int:
        f0, f1 = 0, -inf
        for v in nums:
            f0, f1 = max(f0 + v, f1 + (v ^ k)), max(f1 + v, f0 + (v ^ k))
        return f0
