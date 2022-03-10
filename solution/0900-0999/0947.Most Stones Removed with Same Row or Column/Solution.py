class Solution:
    def removeStones(self, stones: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = 10010
        p = list(range(n << 1))
        for x, y in stones:
            p[find(x)] = find(y + n)

        s = {find(x) for x, _ in stones}
        return len(stones) - len(s)
