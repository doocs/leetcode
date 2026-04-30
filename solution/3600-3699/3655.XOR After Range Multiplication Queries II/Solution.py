class Solution:
    def xorAfterQueries(self, nums: List[int], queries: List[List[int]]) -> int:
        MOD = 1_000_000_007
        n = len(nums)
        B = int(math.isqrt(n)) + 1

        # events[k][res] = list of (t, v)
        events = [[[] for _ in range(k)] for k in range(B + 1)]

        for l, r, k, v in queries:
            if k > B:
                for idx in range(l, r + 1, k):
                    nums[idx] = nums[idx] * v % MOD
            else:
                res = l % k
                t1 = (l - res) // k
                t2 = (r - res) // k
                events[k][res].append((t1, v))

                if t2 + 1 <= (n - 1 - res) // k:
                    invv = pow(v, MOD - 2, MOD)
                    events[k][res].append((t2 + 1, invv))

        for k in range(1, B + 1):
            for res in range(k):
                ev = events[k][res]
                if not ev:
                    continue

                ev.sort()
                comp = []
                for t, val in ev:
                    if comp and comp[-1][0] == t:
                        comp[-1] = (t, comp[-1][1] * val % MOD)
                    else:
                        comp.append([t, val])

                cur = 1
                ptr = 0
                t = 0
                idx = res
                while idx < n:
                    while ptr < len(comp) and comp[ptr][0] == t:
                        cur = cur * comp[ptr][1] % MOD
                        ptr += 1
                    nums[idx] = nums[idx] * cur % MOD
                    idx += k
                    t += 1

        xr = 0
        for x in nums:
            xr ^= x
        return xr
