class Solution:
    def minMergeCost(self, lists: List[List[int]]) -> int:
        n = len(lists)
        vals = sorted({x for v in lists for x in v})
        cnt = [0] * (1 << n)
        med = [0] * (1 << n)
        for i in range(1, 1 << n):
            for j, v in enumerate(lists):
                if i >> j & 1:
                    cnt[i] += len(v)
            need = (cnt[i] + 1) // 2
            l, r = 0, len(vals) - 1
            while l < r:
                mid = (l + r) >> 1
                le = 0
                b = i
                while b:
                    t = (b & -b).bit_length() - 1
                    le += bisect_right(lists[t], vals[mid])
                    if le >= need:
                        break
                    b &= b - 1
                if le >= need:
                    r = mid
                else:
                    l = mid + 1
            med[i] = vals[l]

        f = [inf] * (1 << n)
        for i in range(1, 1 << n):
            if i.bit_count() == 1:
                f[i] = 0
                continue
            j = (i - 1) & i
            while j:
                k = i ^ j
                if j <= k:
                    f[i] = min(f[i], f[j] + f[k] + abs(med[j] - med[k]))
                j = (j - 1) & i
            f[i] += cnt[i]
        return f[-1]
