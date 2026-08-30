class Solution:
    def countSpecialIntegers(self, nums: List[int]) -> int:
        cnt = Counter(x for i, x in enumerate(nums) if i == 0 or x != nums[i - 1])
        return sum(v == 1 for v in cnt.values())
