class Solution:
    def getSneakyNumbers(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        return [x for x, v in cnt.items() if v == 2]
