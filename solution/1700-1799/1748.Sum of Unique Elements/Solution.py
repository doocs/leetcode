class Solution:
    def sumOfUnique(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        return sum(x for x, v in cnt.items() if v == 1)
