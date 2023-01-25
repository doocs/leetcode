class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        ans = 0
        for i, a in enumerate(s):
            for j, b in enumerate(t):
                if a != b:
                    l = r = 1
                    while i >= l and j >= l and s[i - l] == t[j - l]:
                        l += 1
                    while i + r < len(s) and j + r < len(t) and s[i + r] == t[j + r]:
                        r += 1
                    ans += l * r
        return ans
