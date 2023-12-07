class Solution:
    def countDivisibleSubstrings(self, word: str) -> int:
        d = ["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"]
        mp = {}
        for i, s in enumerate(d, 1):
            for c in s:
                mp[c] = i
        ans = 0
        for i in range(1, 10):
            cnt = defaultdict(int)
            cnt[0] = 1
            s = 0
            for c in word:
                s += mp[c] - i
                ans += cnt[s]
                cnt[s] += 1
        return ans
