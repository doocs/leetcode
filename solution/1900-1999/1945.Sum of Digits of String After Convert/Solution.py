class Solution:
    def getLucky(self, s: str, k: int) -> int:
        s = ''.join([str(ord(c) - ord('a') + 1) for c in s])
        for _ in range(k):
            t = 0
            for c in s:
                t += ord(c) - ord('0')
            s = str(t)
        return int(s)
