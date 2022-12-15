class Solution:
    def countNicePairs(self, nums: List[int]) -> int:
        def rev(x):
            y = 0
            while x:
                y = y * 10 + x % 10
                x //= 10
            return y

        cnt = Counter(x - rev(x) for x in nums)
        ans = 0
        mod = 10**9 + 7
        for v in cnt.values():
            ans = (ans + v * (v - 1) // 2) % mod
        return ans
