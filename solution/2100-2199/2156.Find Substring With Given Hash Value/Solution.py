class Solution:
    def subStrHash(
        self, s: str, power: int, modulo: int, k: int, hashValue: int
    ) -> str:
        h, n = 0, len(s)
        p = 1
        for i in range(n - 1, n - 1 - k, -1):
            val = ord(s[i]) - ord("a") + 1
            h = ((h * power) + val) % modulo
            if i != n - k:
                p = p * power % modulo
        j = n - k
        for i in range(n - 1 - k, -1, -1):
            pre = ord(s[i + k]) - ord("a") + 1
            cur = ord(s[i]) - ord("a") + 1
            h = ((h - pre * p) * power + cur) % modulo
            if h == hashValue:
                j = i
        return s[j : j + k]
