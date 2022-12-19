class Solution:
    def similarPairs(self, words: List[str]) -> int:
        ans = 0
        cnt = Counter()
        for w in words:
            v = 0
            for c in w:
                v |= 1 << (ord(c) - ord("A"))
            ans += cnt[v]
            cnt[v] += 1
        return ans
