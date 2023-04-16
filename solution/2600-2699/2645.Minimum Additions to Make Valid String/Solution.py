class Solution:
    def addMinimum(self, word: str) -> int:
        s = 'abc'
        ans, n = 0, len(word)
        i = j = 0
        while j < n:
            if word[j] != s[i]:
                ans += 1
            else:
                j += 1
            i = (i + 1) % 3
        if word[-1] != 'c':
            ans += 1 if word[-1] == 'b' else 2
        return ans
