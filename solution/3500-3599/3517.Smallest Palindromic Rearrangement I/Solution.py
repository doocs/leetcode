class Solution:
    def smallestPalindrome(self, s: str) -> str:
        cnt = Counter(s)
        t = []
        ch = ""
        for c in ascii_lowercase:
            v = cnt[c] // 2
            t.append(c * v)
            cnt[c] -= v * 2
            if cnt[c] == 1:
                ch = c
        ans = "".join(t)
        ans = ans + ch + ans[::-1]
        return ans
