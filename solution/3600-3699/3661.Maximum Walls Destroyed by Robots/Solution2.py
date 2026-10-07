class Solution:
    def maxWalls(self, robots: List[int], distance: List[int], walls: List[int]) -> int:
        n = len(robots)
        arr = sorted(zip(robots, distance), key=lambda x: x[0])
        walls.sort()
        f = [[0, 0] for _ in range(n)]
        for i in range(n):
            for j in range(2):
                left = arr[i][0] - arr[i][1]
                if i:
                    left = max(left, arr[i - 1][0] + 1)
                l = bisect_left(walls, left)
                r = bisect_left(walls, arr[i][0] + 1)
                ans = (f[i - 1][0] if i else 0) + r - l
                right = arr[i][0] + arr[i][1]
                if i + 1 < n:
                    if j == 0:
                        right = min(right, arr[i + 1][0] - arr[i + 1][1] - 1)
                    else:
                        right = min(right, arr[i + 1][0] - 1)
                l = bisect_left(walls, arr[i][0])
                r = bisect_left(walls, right + 1)
                ans = max(ans, (f[i - 1][1] if i else 0) + r - l)
                f[i][j] = ans
        return f[n - 1][1]
