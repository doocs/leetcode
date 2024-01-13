class Solution:
    def shiftingLetters(self, s: str, shifts: List[int]) -> str:
        n = len(s)
        d = [0] * (n + 1)
        for i, c in enumerate(s):
            v = ord(c) - ord('a')
            d[i] += v
            d[i + 1] -= v
        for i, x in enumerate(shifts):
            d[0] += x
            d[i + 1] -= x
        t = 0
        ans = []
        for i in range(n):
            d[i] %= 26
            ans.append(ascii_lowercase[d[i]])
            d[i + 1] += d[i]
        return ''.join(ans)
