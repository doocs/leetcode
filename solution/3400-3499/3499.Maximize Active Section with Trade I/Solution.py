class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        n = len(s)
        ans = i = 0
        pre, mx = -inf, 0
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            cur = j - i
            if s[i] == "1":
                ans += cur
            else:
                mx = max(mx, pre + cur)
                pre = cur
            i = j
        ans += mx
        return ans
