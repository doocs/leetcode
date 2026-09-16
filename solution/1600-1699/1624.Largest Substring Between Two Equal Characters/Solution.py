class Solution:
    def maxLengthBetweenEqualCharacters(self, s: str) -> int:
        d = [-1] * 26
        ans = -1
        for i, c in enumerate(s):
            j = ord(c) - ord("a")
            if d[j] == -1:
                d[j] = i
            else:
                ans = max(ans, i - d[j] - 1)
        return ans
