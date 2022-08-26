class Solution:
    def stringShift(self, s: str, shift: List[List[int]]) -> str:
        x = 0
        for a, b in shift:
            if a == 0:
                b = -b
            x += b
        x %= len(s)
        return s[-x:] + s[:-x]
