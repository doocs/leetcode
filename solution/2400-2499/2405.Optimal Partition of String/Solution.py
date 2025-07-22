class Solution:
    def partitionString(self, s: str) -> int:
        ans, mask = 1, 0
        for x in map(lambda c: ord(c) - ord("a"), s):
            if mask >> x & 1:
                ans += 1
                mask = 0
            mask |= 1 << x
        return ans
