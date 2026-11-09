class Solution:
    def stringShift(self, s: str, shift: list[list[int]]) -> str:
        x = sum((b if a else -b) for a, b in shift)
        x %= len(s)
        return s[-x:] + s[:-x]
