class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        cnt = Counter()
        ans = cur = 0
        i = 0
        for x in nums:
            cur += cnt[x]
            cnt[x] += 1
            while cur - cnt[nums[i]] + 1 >= k:
                cnt[nums[i]] -= 1
                cur -= cnt[nums[i]]
                i += 1
            if cur >= k:
                ans += i + 1
        return ans
