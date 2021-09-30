class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t) or s == t:
            return False
        n = len(s)
        chars = [0] * 26
        for i in range(n):
            chars[ord(s[i]) - ord('a')] += 1
            chars[ord(t[i]) - ord('a')] -= 1
        for c in chars:
            if c != 0:
                return False
        return True
