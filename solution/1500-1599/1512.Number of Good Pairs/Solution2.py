class Solution:
    def numIdenticalPairs(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return sum(v * (v - 1) for v in cnt.values()) >> 1
