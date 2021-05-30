class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        def transfer(word):
            res = 0
            for c in word:
                res *= 10
                res += (ord(c) - ord('a'))
            return res
        return transfer(firstWord) + transfer(secondWord) == transfer(targetWord)
