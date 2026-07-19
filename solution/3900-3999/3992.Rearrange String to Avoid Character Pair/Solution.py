class Solution:
    def rearrangeString(self, s: str, x: str, y: str) -> str:
        t = list(s)
        i = 0
        for j, c in enumerate(t):
            if c == y:
                t[i], t[j] = c, t[i]
                i += 1
        return ''.join(t)
