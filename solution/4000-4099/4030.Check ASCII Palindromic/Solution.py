class Solution:
    def isPalindromic(self, s: str) -> bool:
        t = ''.join(format(ord(c), '08b') for c in s)
        return t == t[::-1]
