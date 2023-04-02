class Solution:
    def findTheLongestBalancedSubstring(self, s: str) -> int:
        ans = zero = one = 0
        for c in s:
            if c == '0':
                if one:
                    zero = one = 0
                zero += 1
            else:
                one += 1
                ans = max(ans, 2 * min(one, zero))
        return ans
