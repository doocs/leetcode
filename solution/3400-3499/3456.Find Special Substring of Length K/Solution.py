class Solution:
    def hasSpecialSubstring(self, s: str, k: int) -> bool:
        l, n = 0, len(s)
        while l < n:
            r = l
            while r < n and s[r] == s[l]:
                r += 1
            if r - l == k:
                return True
            l = r
        return False
