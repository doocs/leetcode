class Solution:
    def fullBloomFlowers(
        self, flowers: List[List[int]], people: List[int]
    ) -> List[int]:
        d = defaultdict(int)
        for st, ed in flowers:
            d[st] += 1
            d[ed + 1] -= 1
        ts = sorted(d)
        s = i = 0
        m = len(people)
        ans = [0] * m
        for t, j in sorted(zip(people, range(m))):
            while i < len(ts) and ts[i] <= t:
                s += d[ts[i]]
                i += 1
            ans[j] = s
        return ans
