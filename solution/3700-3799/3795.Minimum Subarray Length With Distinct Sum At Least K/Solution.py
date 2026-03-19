class Solution:
    def minLength(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        n = len(nums)
        ans = n + 1
        s = l = 0
        for r, x in enumerate(nums):
            cnt[x] += 1
            if cnt[x] == 1:
                s += x
            while s >= k:
                ans = min(ans, r - l + 1)
                cnt[nums[l]] -= 1
                if cnt[nums[l]] == 0:
                    s -= nums[l]
                l += 1
        return -1 if ans > n else ans
