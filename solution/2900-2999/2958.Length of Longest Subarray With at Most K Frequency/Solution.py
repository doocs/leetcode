class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        ans = l = 0
        cnt = defaultdict(int)
        for r, x in enumerate(nums):
            cnt[x] += 1
            while cnt[x] > k:
                cnt[nums[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
