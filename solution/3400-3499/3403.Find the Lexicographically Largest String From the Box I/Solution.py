class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        if numFriends == 1:
            return word
        n = len(word)
        ans = ""
        for i in range(n):
            k = min(n - i, n - numFriends + 1)
            ans = max(ans, word[i : i + k])
        return ans
