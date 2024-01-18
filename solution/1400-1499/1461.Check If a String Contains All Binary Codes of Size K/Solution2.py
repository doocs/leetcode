class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        if len(s) - k + 1 < (1 << k):
            return False
        vis = [False] * (1 << k)
        num = int(s[:k], 2)
        vis[num] = True
        for i in range(k, len(s)):
            a = (ord(s[i - k]) - ord('0')) << (k - 1)
            b = ord(s[i]) - ord('0')
            num = ((num - a) << 1) + b
            vis[num] = True
        return all(v for v in vis)
