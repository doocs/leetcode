class Solution:
    def majorityFrequencyGroup(self, s: str) -> str:
        cnt = Counter(s)
        f = defaultdict(list)
        for c, v in cnt.items():
            f[v].append(c)
        mx = mv = 0
        ans = []
        for v, cs in f.items():
            if mx < len(cs) or (mx == len(cs) and mv < v):
                mx = len(cs)
                mv = v
                ans = cs
        return "".join(ans)
