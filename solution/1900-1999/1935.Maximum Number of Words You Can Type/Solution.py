class Solution:
    def canBeTypedWords(self, text: str, brokenLetters: str) -> int:
        letters = set(brokenLetters)
        res = 0
        for word in text.split():
            find = False
            for letter in letters:
                if letter in word:
                    find = True
                    break
            if not find:
                res += 1
        return res
