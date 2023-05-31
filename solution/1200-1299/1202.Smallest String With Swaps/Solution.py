class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(s)
        p = list(range(n))
        for a, b in pairs:
            p[find(a)] = find(b)
        d = defaultdict(list)
        for i, c in enumerate(s):
            d[find(i)].append(c)
        for i in d.keys():
            d[i].sort(reverse=True)
        return "".join(d[find(i)].pop() for i in range(n))
