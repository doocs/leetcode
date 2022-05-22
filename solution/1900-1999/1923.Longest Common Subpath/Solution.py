class Solution:
    def longestCommonSubpath(self, n: int, paths: List[List[int]]) -> int:
        def get(l, r, h):
            return (h[r] - h[l - 1] * p[r - l + 1]) % mod

        def check(l):
            cnt = Counter()
            for k, path in enumerate(paths):
                vis = set()
                for i in range(len(path) - l + 1):
                    j = i + l - 1
                    x = get(i + 1, j + 1, hh[k])
                    if x not in vis:
                        vis.add(x)
                        cnt[x] += 1
            return max(cnt.values()) == len(paths)

        base = 133331
        mod = 2**64 + 1
        p = [0] * 100010
        p[0] = 1
        for i in range(1, len(p)):
            p[i] = (p[i - 1] * base) % mod
        hh = []
        for path in paths:
            h = [0] * (len(path) + 10)
            for j, c in enumerate(path):
                h[j + 1] = (h[j] * base) % mod + c
            hh.append(h)
        left, right = 0, min(len(path) for path in paths)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
