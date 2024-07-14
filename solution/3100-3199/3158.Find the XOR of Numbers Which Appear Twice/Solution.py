class Solution:
    def duplicateNumbersXOR(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return reduce(xor, [x for x, v in cnt.items() if v == 2], 0)
