class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(s)
        p = list(range(n))
        for a, b in pairs:
            p[find(a)] = find(b)
        mp = defaultdict(list)
        for i, c in enumerate(s):
            heappush(mp[find(i)], c)
        return ''.join(heappop(mp[find(i)]) for i in range(n))
