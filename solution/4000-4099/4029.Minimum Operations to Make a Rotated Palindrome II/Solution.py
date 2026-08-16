import numpy as np


class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)

        size = 1
        while size < 2 * n:
            size <<= 1

        nums = np.array([ord(c) - ord('a') for c in s], dtype=np.int64)

        cost = np.zeros(26)

        for t in range(26):
            for z in range(26):
                cost[t] += min(z, 26 - z) * math.cos(2 * math.pi * t * z / 26)

        dp = np.zeros(n)

        for t in range(14):
            theta = 2 * math.pi * t / 26

            a = np.exp(1j * theta * nums)
            a = np.pad(a, (0, size - n))

            b = np.conj(a)

            fa = np.fft.fft(a)
            fb = np.fft.fft(b)

            conv = np.fft.ifft(fa * fb).real

            mult = 1 if t == 0 or t == 13 else 2

            dp += mult * cost[t] * (conv[:n] + conv[n : 2 * n])

        ans = inf

        for k in range(n):
            c = (2 * k + n - 1) % n
            d = round(dp[c] / 52)

            ans = min(ans, k + d)

        return ans
