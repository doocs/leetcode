class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        ans = ""
        for i in range(n):
            for j in range(i + k, n + 1):
                t = s[i:j]
                if t.count("1") == k and (
                    not ans or j - i < len(ans) or (j - i == len(ans) and t < ans)
                ):
                    ans = t
        return ans
