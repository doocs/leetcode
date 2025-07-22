class Solution:
    def longestPrefix(self, s: str) -> str:
        s += "#"
        n = len(s)
        next = [0] * n
        next[0] = -1
        i, j = 2, 0
        while i < n:
            if s[i - 1] == s[j]:
                j += 1
                next[i] = j
                i += 1
            elif j:
                j = next[j]
            else:
                next[i] = 0
                i += 1
        return s[: next[-1]]
