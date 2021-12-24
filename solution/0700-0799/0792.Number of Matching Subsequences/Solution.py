class Solution:
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:
        buckets = defaultdict(list)
        for word in words:
            buckets[word[0]].append(word)
        res = 0
        for c in s:
            old = buckets[c][::1]
            buckets[c].clear()
            for t in old:
                if len(t) == 1:
                    res += 1
                else:
                    buckets[t[1]].append(t[1:])
        return res
