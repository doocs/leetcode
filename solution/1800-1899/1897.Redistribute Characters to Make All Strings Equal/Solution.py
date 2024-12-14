class Solution:
    def makeEqual(self, words: List[str]) -> bool:
        cnt = Counter()
        for w in words:
            for c in w:
                cnt[c] += 1
        n = len(words)
        return all(v % n == 0 for v in cnt.values())
