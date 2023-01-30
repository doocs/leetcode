class Solution:
    def canConvertString(self, s: str, t: str, k: int) -> bool:
        if len(s) != len(t):
            return False
        cnt = [0] * 26
        for a, b in zip(s, t):
            x = (ord(b) - ord(a) + 26) % 26
            cnt[x] += 1
        for i in range(1, 26):
            if i + 26 * (cnt[i] - 1) > k:
                return False
        return True
