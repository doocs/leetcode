class Solution:
    def xorAfterQueries(self, nums: list[int], queries: list[list[int]]) -> int:
        MOD = 10**9 + 7
        copy = list(nums)
        for query in queries:
            li, ri, ki, vi = query
            idx = li
            while idx <= ri:
                copy[idx] = (copy[idx] * vi) % MOD
                idx += ki
        xor_result = 0
        for num in copy:
            xor_result ^= num
        return xor_result
