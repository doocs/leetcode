class Solution:
    def maxValue(self, n: str, x: int) -> str:
        if n[0] != '-':
            for i, c in enumerate(n):
                if int(c) < x:
                    return n[:i] + str(x) + n[i:]
            return n + str(x)
        else:
            for i, c in enumerate(n[1:]):
                if int(c) > x:
                    return n[: i + 1] + str(x) + n[i + 1 :]
            return n + str(x)
