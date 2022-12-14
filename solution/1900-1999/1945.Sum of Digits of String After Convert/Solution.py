class Solution:
    def getLucky(self, s: str, k: int) -> int:
        s = ''.join(str(ord(c) - ord('a') + 1) for c in s)
        for _ in range(k):
            t = sum(int(c) for c in s)
            s = str(t)
        return int(s)
