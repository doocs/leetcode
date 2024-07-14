class Solution:
    def isGood(self, nums: List[int]) -> bool:
        cnt = Counter(nums)
        n = len(nums) - 1
        return cnt[n] == 2 and all(cnt[i] for i in range(1, n))
