class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        sum = j = 0
        ans = 0
        for i in range(n):
            sum += abs(ord(s[i]) - ord(t[i]))
            while sum > maxCost:
                sum -= abs(ord(s[j]) - ord(t[j]))
                j += 1
            ans = max(ans, i - j + 1)
        return ans
