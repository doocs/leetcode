class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        s = list(s)
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalpha():
                i += 1
            while i < j and not s[j].isalpha():
                j -= 1
            if i < j:
                s[i], s[j] = s[j], s[i]
                i, j = i + 1, j - 1
        return ''.join(s)
