class Solution:
    def minTransfers(self, transactions: List[List[int]]) -> int:
        g = defaultdict(int)
        for f, t, x in transactions:
            g[f] -= x
            g[t] += x
        nums = [x for x in g.values() if x]
        m = len(nums)
        f = [inf] * (1 << m)
        f[0] = 0
        for i in range(1, 1 << m):
            s = 0
            for j, x in enumerate(nums):
                if i >> j & 1:
                    s += x
            if s == 0:
                f[i] = i.bit_count() - 1
                j = (i - 1) & i
                while j > 0:
                    f[i] = min(f[i], f[j] + f[i ^ j])
                    j = (j - 1) & i
        return f[-1]
