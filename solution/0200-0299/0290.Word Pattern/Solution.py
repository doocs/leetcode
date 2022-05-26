class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        s = s.split(' ')
        n = len(pattern)
        if n != len(s):
            return False
        c2str, str2c = defaultdict(), defaultdict()
        for i in range(n):
            k, v = pattern[i], s[i]
            if k in c2str and c2str[k] != v:
                return False
            if v in str2c and str2c[v] != k:
                return False
            c2str[k], str2c[v] = v, k
        return True
