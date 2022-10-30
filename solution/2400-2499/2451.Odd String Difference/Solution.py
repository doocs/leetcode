class Solution:
    def oddString(self, words: List[str]) -> str:
        cnt = defaultdict(list)
        for w in words:
            d = [str(ord(b) - ord(a)) for a, b in pairwise(w)]
            cnt[','.join(d)].append(w)
        return next(v[0] for v in cnt.values() if len(v) == 1)
