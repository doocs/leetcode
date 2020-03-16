import collections

class Solution:
    def firstUniqChar(self, s: str) -> str:
        if s == '':
            return ' '
        cache = collections.OrderedDict()
        for c in s:
            cache[c] = 1 if cache.get(c) is None else cache[c] + 1
        for k, v in cache.items():
            if v == 1:
                return k
        return ' '