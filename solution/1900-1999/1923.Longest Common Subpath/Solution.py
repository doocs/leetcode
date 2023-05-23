class Solution:
    def longestCommonSubpath(self, n: int, paths: List[List[int]]) -> int:
        def check(k: int) -> bool:
            cnt = Counter()
            for h in hh:
                vis = set()
                for i in range(1, len(h) - k + 1):
                    j = i + k - 1
                    x = (h[j] - h[i - 1] * p[j - i + 1]) % mod
                    if x not in vis:
                        vis.add(x)
                        cnt[x] += 1
            return max(cnt.values()) == m

        m = len(paths)
        mx = max(len(path) for path in paths)
        base = 133331
        mod = 2**64 + 1
        p = [0] * (mx + 1)
        p[0] = 1
        for i in range(1, len(p)):
            p[i] = p[i - 1] * base % mod
        hh = []
        for path in paths:
            k = len(path)
            h = [0] * (k + 1)
            for i, x in enumerate(path, 1):
                h[i] = h[i - 1] * base % mod + x
            hh.append(h)
        l, r = 0, min(len(path) for path in paths)
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
