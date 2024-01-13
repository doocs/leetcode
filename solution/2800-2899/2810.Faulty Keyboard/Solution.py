class Solution:
    def finalString(self, s: str) -> str:
        t = []
        for c in s:
            if c == "i":
                t = t[::-1]
            else:
                t.append(c)
        return "".join(t)
