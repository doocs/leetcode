class Solution:
    def countBadPairs(self, nums: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for i, x in enumerate(nums):
            ans += i - cnt[i - x]
            cnt[i - x] += 1
        return ans
