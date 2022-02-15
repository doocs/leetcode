class Solution:
    def minimumHammingDistance(
        self, source: List[int], target: List[int], allowedSwaps: List[List[int]]
    ) -> int:
        n = len(source)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i, j in allowedSwaps:
            p[find(i)] = find(j)

        mp = defaultdict(Counter)
        for i in range(n):
            mp[find(i)][source[i]] += 1
        res = 0
        for i in range(n):
            if mp[find(i)][target[i]] > 0:
                mp[find(i)][target[i]] -= 1
            else:
                res += 1
        return res
