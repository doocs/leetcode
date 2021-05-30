class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        n = 9
        firstWord = 'a' * (n - len(firstWord)) + firstWord
        secondWord = 'a' * (n - len(secondWord)) + secondWord
        targetWord = 'a' * (n - len(targetWord)) + targetWord
        carry = 0
        while n > 0:
            n -= 1
            i = ord(firstWord[n]) - ord('a')
            j = ord(secondWord[n]) - ord('a')
            carry, s = divmod(i + j + carry, 10)
            if targetWord[n] != chr(ord('a') + s):
                return False
        return True
