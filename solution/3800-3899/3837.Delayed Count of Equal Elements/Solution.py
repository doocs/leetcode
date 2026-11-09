class Solution:
    def delayedCount(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        cnt = Counter()
        ans = [0] * n
        for i in range(n - k - 2, -1, -1):
            cnt[nums[i + k + 1]] += 1
            ans[i] = cnt[nums[i]]
        return ans
