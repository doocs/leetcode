class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        i = j = cnt = 0
        n = len(s)
        ans = ""
        while j < n:
            cnt += s[j] == "1"
            while cnt > k or (i < j and s[i] == "0"):
                cnt -= s[i] == "1"
                i += 1
            j += 1
            if cnt == k and (
                not ans or j - i < len(ans) or (j - i == len(ans) and s[i:j] < ans)
            ):
                ans = s[i:j]
        return ans
