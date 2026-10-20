MOD = 10**9 + 7


class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        n = len(nums)

        class Node:
            __slots__ = ("l", "r", "sum", "sum_sq", "lazy")

            def __init__(self):
                self.l = self.r = 0
                self.sum = self.sum_sq = self.lazy = 0

        tr = [Node() for _ in range(n << 2)]

        def build(u: int, l: int, r: int) -> None:
            tr[u].l, tr[u].r = l, r
            if l == r:
                return
            mid = (l + r) >> 1
            build(u << 1, l, mid)
            build(u << 1 | 1, mid + 1, r)

        def apply(u: int, v: int) -> None:
            length = tr[u].r - tr[u].l + 1
            tr[u].sum_sq += 2 * v * tr[u].sum + v * v * length
            tr[u].sum += v * length
            tr[u].lazy += v

        def pushdown(u: int) -> None:
            if tr[u].lazy:
                apply(u << 1, tr[u].lazy)
                apply(u << 1 | 1, tr[u].lazy)
                tr[u].lazy = 0

        def pushup(u: int) -> None:
            tr[u].sum = tr[u << 1].sum + tr[u << 1 | 1].sum
            tr[u].sum_sq = tr[u << 1].sum_sq + tr[u << 1 | 1].sum_sq

        def modify(u: int, l: int, r: int, v: int) -> None:
            if tr[u].l >= l and tr[u].r <= r:
                apply(u, v)
                return
            pushdown(u)
            mid = (tr[u].l + tr[u].r) >> 1
            if l <= mid:
                modify(u << 1, l, r, v)
            if r > mid:
                modify(u << 1 | 1, l, r, v)
            pushup(u)

        build(1, 0, n - 1)
        last = {}
        ans = 0
        for i, x in enumerate(nums):
            p = last.get(x, -1)
            modify(1, p + 1, i, 1)
            last[x] = i
            ans = (ans + tr[1].sum_sq) % MOD
        return ans
