class Solution:
    def divideArray(self, nums: list[int]) -> bool:
        cnt = Counter(nums)
        return all(v % 2 == 0 for v in cnt.values())
