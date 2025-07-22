class Solution:
    def reportSpam(self, message: List[str], bannedWords: List[str]) -> bool:
        s = set(bannedWords)
        return sum(w in s for w in message) >= 2
