class Solution:
    def isGood(self, nums: List[int]) -> bool:
        n = len(nums) - 1
        cnt = Counter(nums)
        cnt[n] -= 2
        for i in range(1, n):
            cnt[i] -= 1
        return all(v == 0 for v in cnt.values())
