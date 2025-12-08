class Solution:
    def maxSameLengthRuns(self, s: str) -> int:
        cnt = Counter()
        for _, g in groupby(s):
            cnt[len(list(g))] += 1
        return max(cnt.values())
