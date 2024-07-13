class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        ans = cost = l = 0
        for r in range(n):
            cost += abs(ord(s[r]) - ord(t[r]))
            while cost > maxCost:
                cost -= abs(ord(s[l]) - ord(t[l]))
                l += 1
            ans = max(ans, r - l + 1)
        return ans
