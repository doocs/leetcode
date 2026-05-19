class Solution:
    def powerUpdate(
        self, nums: list[int], p: int, queries: list[list[int]]
    ) -> list[int]:
        l = SortedList()
        r = SortedList()
        for x in nums:
            r.add(x)
            if len(r) > queries[0][0]:
                l.add(r.pop(0))
        ans = []
        mod = 10**9 + 7
        for val, k in queries:
            r.add(val)
            l.add(r.pop(0))
            while len(r) < k:
                r.add(l.pop())
            while len(r) > k:
                l.add(r.pop(0))
            x = r[0]
            p = pow(p, x, mod)
            ans.append(p)
        return ans
