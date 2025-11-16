class Solution:
    def minLengthAfterRemovals(self, s: str) -> int:
        a = s.count("a")
        b = len(s) - a
        return abs(a - b)
