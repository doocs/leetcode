class Solution:
    def partitionString(self, s: str) -> int:
        ans, v = 1, 0
        for c in s:
            i = ord(c) - ord('a')
            if (v >> i) & 1:
                v = 0
                ans += 1
            v |= 1 << i
        return ans
