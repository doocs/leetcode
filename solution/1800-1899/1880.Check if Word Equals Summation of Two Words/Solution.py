class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        def f(s: str) -> int:
            ans, a = 0, ord("a")
            for c in map(ord, s):
                x = c - a
                ans = ans * 10 + x
            return ans

        return f(firstWord) + f(secondWord) == f(targetWord)
