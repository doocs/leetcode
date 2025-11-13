class Solution:
    def distinctPoints(self, s: str, k: int) -> int:
        n = len(s)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        x = y = 0
        for i, c in enumerate(s, 1):
            if c == "U":
                y += 1
            elif c == "D":
                y -= 1
            elif c == "L":
                x -= 1
            else:
                x += 1
            f[i] = x
            g[i] = y
        st = set()
        for i in range(k, n + 1):
            a = f[n] - (f[i] - f[i - k])
            b = g[n] - (g[i] - g[i - k])
            st.add((a, b))
        return len(st)
