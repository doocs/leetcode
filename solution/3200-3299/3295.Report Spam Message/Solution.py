class Solution:
    def reportSpam(self, message: list[str], bannedWords: list[str]) -> bool:
        s = set(bannedWords)
        return sum(w in s for w in message) >= 2
