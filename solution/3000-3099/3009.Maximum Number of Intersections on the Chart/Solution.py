class Solution:
    def maxIntersectionCount(self, y: List[int]) -> int:
        n = len(y)
        line = defaultdict(int)
        for i in range(1, n):
            start = 2 * y[i - 1]
            end = 2 * y[i] + (0 if i == n - 1 else (-1 if y[i] > y[i - 1] else 1))
            line[min(start, end)] += 1
            line[max(start, end) + 1] -= 1
        ans = intersection = 0
        for _, count in sorted(line.items()):
            intersection += count
            ans = max(ans, intersection)
        return ans
