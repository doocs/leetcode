class Solution:
    def divideString(self, s: str, k: int, fill: str) -> list[str]:
        return [s[i : i + k].ljust(k, fill) for i in range(0, len(s), k)]
