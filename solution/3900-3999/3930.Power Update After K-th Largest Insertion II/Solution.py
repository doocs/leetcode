class Solution:
    def powerUpdate(
        self, nums: list[int], p: int, queries: list[list[int]]
    ) -> list[int]:
        ans = []
        sl = SortedList(nums)
        mod = 10**9 + 7
        for val, k in queries:
            sl.add(val)
            p = pow(p, sl[-k], mod)
            ans.append(p)
        return ans
