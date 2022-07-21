class Solution:
    def balancedStringSplit(self, s: str) -> int:
        ans = l = 0
        for c in s:
            if c == 'L':
                l += 1
            else:
                l -= 1
            if l == 0:
                ans += 1
        return ans
