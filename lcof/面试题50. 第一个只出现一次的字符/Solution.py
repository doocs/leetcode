class Solution:
    def firstUniqChar(self, s: str) -> str:
        counter = collections.Counter(s)
        for c in s:
            if counter[c] == 1:
                return c
        return ' '
