class Solution:
    def checkIfCanBreak(self, s1: str, s2: str) -> bool:
        cs1 = sorted(s1)
        cs2 = sorted(s2)
        return all(a >= b for a, b in zip(cs1, cs2)) or all(
            a <= b for a, b in zip(cs1, cs2)
        )
