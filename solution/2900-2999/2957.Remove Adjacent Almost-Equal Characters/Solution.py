class Solution:
    def removeAlmostEqualCharacters(self, word: str) -> int:
        ans = 0
        i, n = 1, len(word)
        while i < n:
            if abs(ord(word[i]) - ord(word[i - 1])) < 2:
                ans += 1
                i += 2
            else:
                i += 1
        return ans
