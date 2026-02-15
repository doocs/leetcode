class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        cnt = Counter()
        for w in words:
            if len(w) >= k:
                cnt[w[:k]] += 1
        return sum(v > 1 for v in cnt.values())
