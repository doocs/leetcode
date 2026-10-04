class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        n = len(s)
        masks = [1 << (ord(c) - ord("a")) for c in s]
        reach = [set() for _ in range(n + 1)]
        reach[0].add((0, 1))
        for i, v in enumerate(masks):
            for cur, t in reach[i]:
                nxt = cur | v
                if nxt.bit_count() > k:
                    reach[i + 1].add((v, t))
                else:
                    reach[i + 1].add((nxt, t))
                if t:
                    for j in range(26):
                        bit = 1 << j
                        nxt = cur | bit
                        if nxt.bit_count() > k:
                            reach[i + 1].add((bit, 0))
                        else:
                            reach[i + 1].add((nxt, 0))

        def get(i: int, cur: int, t: int) -> int:
            if i == n:
                return 1
            return f[i][(cur, t)]

        f = [dict() for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            v = masks[i]
            for cur, t in reach[i]:
                nxt = cur | v
                if nxt.bit_count() > k:
                    ans = get(i + 1, v, t) + 1
                else:
                    ans = get(i + 1, nxt, t)
                if t:
                    for j in range(26):
                        bit = 1 << j
                        nxt = cur | bit
                        if nxt.bit_count() > k:
                            ans = max(ans, get(i + 1, bit, 0) + 1)
                        else:
                            ans = max(ans, get(i + 1, nxt, 0))
                f[i][(cur, t)] = ans
        return f[0][(0, 1)]
