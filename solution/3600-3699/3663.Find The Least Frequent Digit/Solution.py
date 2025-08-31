class Solution:
    def getLeastFrequentDigit(self, n: int) -> int:
        cnt = [0] * 10
        while n:
            n, x = divmod(n, 10)
            cnt[x] += 1
        ans, f = 0, inf
        for x, v in enumerate(cnt):
            if 0 < v < f:
                f = v
                ans = x
        return ans
