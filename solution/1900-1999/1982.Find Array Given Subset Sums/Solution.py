from sortedcontainers import SortedList


class Solution:
    def recoverArray(self, n: int, sums: List[int]) -> List[int]:
        m = -min(sums)
        sl = SortedList(x + m for x in sums)
        sl.remove(0)
        ans = [sl[0]]
        for i in range(1, n):
            for j in range(1 << i):
                if j >> (i - 1) & 1:
                    s = sum(ans[k] for k in range(i) if j >> k & 1)
                    sl.remove(s)
            ans.append(sl[0])
        for i in range(1 << n):
            s = sum(ans[j] for j in range(n) if i >> j & 1)
            if s == m:
                for j in range(n):
                    if i >> j & 1:
                        ans[j] *= -1
                break
        return ans
