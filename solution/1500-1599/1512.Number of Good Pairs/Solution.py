class Solution:
    def numIdenticalPairs(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return sum([x * (x - 1) for x in counter.values()]) >> 1
