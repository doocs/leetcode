class Solution:
    def longestSubarray(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = cur = l = 0
        for r, x in enumerate(nums):
            cnt[x] += 1
            cur += cnt[x] == 2
            while cur > k:
                cnt[nums[l]] -= 1
                cur -= cnt[nums[l]] == 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
