class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(26))
        for a, b in zip(s1, s2):
            x, y = ord(a) - ord("a"), ord(b) - ord("a")
            px, py = find(x), find(y)
            if px < py:
                p[py] = px
            else:
                p[px] = py
        return "".join(chr(find(ord(c) - ord("a")) + ord("a")) for c in baseStr)
