class Solution:
    def oddString(self, words: List[str]) -> str:
        d = defaultdict(list)
        for s in words:
            t = tuple(ord(b) - ord(a) for a, b in pairwise(s))
            d[t].append(s)
        return next(ss[0] for ss in d.values() if len(ss) == 1)
