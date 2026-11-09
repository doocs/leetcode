class Solution:
    def numberOfPairs(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        s = sum(v // 2 for v in cnt.values())
        return [s, len(nums) - s * 2]
