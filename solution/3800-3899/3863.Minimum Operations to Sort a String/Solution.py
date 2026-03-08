class Solution:
    def minOperations(self, s: str) -> int:
        if all(a <= b for a, b in pairwise(s)):
            return 0
        if len(s) == 2:
            return -1
        mn, mx = min(s), max(s)
        if s[0] == mn or s[-1] == mx:
            return 1
        if any(c in [mn, mx] for c in s[1:-1]):
            return 2
        return 3
