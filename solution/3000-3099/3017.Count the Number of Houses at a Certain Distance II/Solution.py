class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        if abs(x - y) <= 1:
            return [2 * x for x in reversed(range(n))]
        cycle_len = abs(x - y) + 1
        n2 = n - cycle_len + 2
        res = [2 * x for x in reversed(range(n2))]
        while len(res) < n:
            res.append(0)
        res2 = [cycle_len * 2] * (cycle_len >> 1)
        if not cycle_len & 1:
            res2[-1] = cycle_len
        res2[0] -= 2
        for i in range(len(res2)):
            res[i] += res2[i]
        if x > y:
            x, y = y, x
        tail1 = x - 1
        tail2 = n - y
        for tail in (tail1, tail2):
            if not tail: continue
            i_mx = (tail + (cycle_len >> 1))
            val_mx = 4 * min((cycle_len - 3) >> 1, tail)
            i_mx2 = i_mx - (1 - (cycle_len & 1))
            res3 = [val_mx] * i_mx
            res3[0] = 0
            res3[1] = 0
            if not cycle_len & 1:
                res3[-1] = 0
            for i, j in enumerate(range(4, val_mx, 4)):
                res3[i + 2] = j
                res3[i_mx2 - i - 1] = j
            for i in range(1, tail + 1):
                res3[i] += 2
            if not cycle_len & 1:
                mn = (cycle_len >> 1)
                for i in range(mn, mn + tail):
                    res3[i] += 2
            for i in range(len(res3)):
                res[i] += res3[i]
        return res
