class Solution:
    def maxValue(self, n: str, x: int) -> str:
        i = 0
        if n[0] == "-":
            i += 1
            while i < len(n) and int(n[i]) <= x:
                i += 1
        else:
            while i < len(n) and int(n[i]) >= x:
                i += 1
        return n[:i] + str(x) + n[i:]
