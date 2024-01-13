class Solution:
    def sumOfFlooredPairs(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(nums)
        mx = max(nums)
        s = [0] * (mx + 1)
        for i in range(1, mx + 1):
            s[i] = s[i - 1] + cnt[i]
        ans = 0
        for y in range(1, mx + 1):
            if cnt[y]:
                d = 1
                while d * y <= mx:
                    ans += cnt[y] * d * (s[min(mx, d * y + y - 1)] - s[d * y - 1])
                    ans %= mod
                    d += 1
        return ans
