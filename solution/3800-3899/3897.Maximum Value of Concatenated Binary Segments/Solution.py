class Solution:
    def maxValue(self, nums1: list[int], nums0: list[int]) -> int:
        MOD = 10**9 + 7
        pairs = list(zip(nums1, nums0))
        b = sum(x + y for x, y in pairs)

        def key(p: tuple[int, int]) -> tuple[int, int, int]:
            x, y = p
            if y == 0:
                return (0, -x, 0)
            if x > 0:
                return (1, -x, y)
            return (2, y, 0)

        pairs.sort(key=key)

        ans = 0
        p = [1] * b
        for i in range(1, b):
            p[i] = p[i - 1] * 2 % MOD

        b -= 1
        for cnt1, cnt0 in pairs:
            while cnt1:
                ans = (ans + p[b]) % MOD
                b -= 1
                cnt1 -= 1
            b -= cnt0
        return ans
