class Solution:
    def beautifulSubarrays(self, nums: List[int]) -> int:
        cnt = Counter({0: 1})
        ans = mask = 0
        for x in nums:
            mask ^= x
            ans += cnt[mask]
            cnt[mask] += 1
        return ans
