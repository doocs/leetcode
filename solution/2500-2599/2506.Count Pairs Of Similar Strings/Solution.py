class Solution:
    def similarPairs(self, words: List[str]) -> int:
        ans = 0
        cnt = Counter()
        for s in words:
            x = 0
            for c in map(ord, s):
                x |= 1 << (c - ord("a"))
            ans += cnt[x]
            cnt[x] += 1
        return ans
