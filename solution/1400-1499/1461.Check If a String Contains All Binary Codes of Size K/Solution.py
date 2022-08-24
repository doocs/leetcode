class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        ss = {s[i : i + k] for i in range(len(s) - k + 1)}
        return len(ss) == 1 << k
