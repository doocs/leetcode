class Solution:
    def breakPalindrome(self, palindrome: str) -> str:
        n = len(palindrome)
        if n == 1:
            return ""
        s = list(palindrome)
        i = 0
        while i < n // 2 and s[i] == "a":
            i += 1
        if i == n // 2:
            s[-1] = "b"
        else:
            s[i] = "a"
        return "".join(s)
