class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        ans = 0
        mod = 10**9 + 7
        cnt = Counter()
        for x in nums:
            y = x - rev(x)
            ans += cnt[y]
            cnt[y] += 1
        return ans % mod
