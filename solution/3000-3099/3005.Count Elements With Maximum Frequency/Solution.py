class Solution:
    def maxFrequencyElements(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        mx = max(cnt.values())
        return sum(x for x in cnt.values() if x == mx)
