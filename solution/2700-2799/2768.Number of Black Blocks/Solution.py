class Solution:
    def countBlackBlocks(
        self, m: int, n: int, coordinates: List[List[int]]
    ) -> List[int]:
        cnt = Counter()
        for x, y in coordinates:
            for a, b in pairwise((0, 0, -1, -1, 0)):
                i, j = x + a, y + b
                if 0 <= i < m - 1 and 0 <= j < n - 1:
                    cnt[(i, j)] += 1
        ans = [0] * 5
        for x in cnt.values():
            ans[x] += 1
        ans[0] = (m - 1) * (n - 1) - len(cnt.values())
        return ans
