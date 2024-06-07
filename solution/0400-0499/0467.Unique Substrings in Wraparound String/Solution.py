class Solution:
    def findSubstringInWraproundString(self, s: str) -> int:
        f = defaultdict(int)
        k = 0
        for i, c in enumerate(s):
            if i and (ord(c) - ord(s[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            f[c] = max(f[c], k)
        return sum(f.values())
