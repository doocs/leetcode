class Solution:
    def largestUniqueNumber(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return next((x for x in range(1000, -1, -1) if cnt[x] == 1), -1)
