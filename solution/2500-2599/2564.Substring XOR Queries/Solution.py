class Solution:
    def substringXorQueries(self, s: str, queries: List[List[int]]) -> List[List[int]]:
        d = {}
        n = len(s)
        for i in range(n):
            x = 0
            for j in range(32):
                if i + j >= n:
                    break
                x = x << 1 | int(s[i + j])
                if x not in d:
                    d[x] = [i, i + j]
                if x == 0:
                    break
        return [d.get(first ^ second, [-1, -1]) for first, second in queries]
