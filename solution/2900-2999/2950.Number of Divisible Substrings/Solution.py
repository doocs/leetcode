class Solution:
    def countDivisibleSubstrings(self, word: str) -> int:
        d = ["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"]
        mp = {}
        for i, s in enumerate(d, 1):
            for c in s:
                mp[c] = i
        ans = 0
        n = len(word)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += mp[word[j]]
                ans += s % (j - i + 1) == 0
        return ans
