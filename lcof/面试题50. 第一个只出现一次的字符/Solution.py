import collections

class Solution:
    def firstUniqChar(self, s: str) -> str:
        counter = collections.Counter()
        for c in s:
            counter[c] += 1
        for c in s:
            if counter[c] == 1:
                return c
        return ' '