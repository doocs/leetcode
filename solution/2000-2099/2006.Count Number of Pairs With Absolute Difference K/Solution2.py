class Solution:
    def countKDifference(self, nums: list[int], k: int) -> int:
        ans = 0
        cnt = Counter()
        for num in nums:
            ans += cnt[num - k] + cnt[num + k]
            cnt[num] += 1
        return ans
