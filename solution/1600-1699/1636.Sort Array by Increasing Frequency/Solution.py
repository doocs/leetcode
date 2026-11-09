class Solution:
    def frequencySort(self, nums: list[int]) -> list[int]:
        cnt = Counter(nums)
        return sorted(nums, key=lambda x: (cnt[x], -x))
