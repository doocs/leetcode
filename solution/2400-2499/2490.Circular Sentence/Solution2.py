class Solution:
    def isCircularSentence(self, s: str) -> bool:
        return s[0] == s[-1] and all(
            c != " " or s[i - 1] == s[i + 1] for i, c in enumerate(s)
        )
