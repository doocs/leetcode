class Solution:
    def smallestStringWithSwaps(self, s: str, pairs: List[List[int]]) -> str:
        n = len(s)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in pairs:
            p[find(a)] = find(b)

        mp = defaultdict(list)
        for i in range(n):
            heapq.heappush(mp[find(i)], s[i])
        chars = list(s)
        for i in range(n):
            chars[i] = heapq.heappop(mp[find(i)])
        return ''.join(chars)
