class Solution:
    def maximumWeight(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        arr = [[e[0], e[1], e[2], i] for i, e in enumerate(intervals)]
        arr.sort()
        nxt = [0] * n
        for i in range(n):
            l, r = i + 1, n
            while l < r:
                mid = (l + r) >> 1
                if arr[mid][0] > arr[i][1]:
                    r = mid
                else:
                    l = mid + 1
            nxt[i] = l
        f = [[0] * 5 for _ in range(n + 1)]
        g = [[[] for _ in range(5)] for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for k in range(1, 5):
                s1, a1 = f[i + 1][k], g[i + 1][k]
                a2 = g[nxt[i]][k - 1][:]
                x = arr[i][3]
                j = 0
                while j < len(a2) and a2[j] < x:
                    j += 1
                a2.insert(j, x)
                s2 = f[nxt[i]][k - 1] + arr[i][2]
                if s2 > s1 or (s2 == s1 and a2 < a1):
                    f[i][k] = s2
                    g[i][k] = a2
                else:
                    f[i][k] = s1
                    g[i][k] = a1
        return g[0][4]
