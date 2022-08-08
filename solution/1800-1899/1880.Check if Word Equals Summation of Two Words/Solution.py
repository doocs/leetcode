class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        def f(s):
            res = 0
            for c in s:
                res = res * 10 + (ord(c) - ord('a'))
            return res

        return f(firstWord) + f(secondWord) == f(targetWord)
