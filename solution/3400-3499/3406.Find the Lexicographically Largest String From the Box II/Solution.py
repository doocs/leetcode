class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        if numFriends == 1:
            return word
        s = self.lastSubstring(word)
        return s[: len(word) - numFriends + 1]

    def lastSubstring(self, s: str) -> str:
        i, j, k = 0, 1, 0
        while j + k < len(s):
            if s[i + k] == s[j + k]:
                k += 1
            elif s[i + k] < s[j + k]:
                i += k + 1
                k = 0
                if i >= j:
                    j = i + 1
            else:
                j += k + 1
                k = 0
        return s[i:]
