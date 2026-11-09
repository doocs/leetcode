class Solution:
    def xorQueries(self, arr: list[int], queries: list[list[int]]) -> list[int]:
        s = list(accumulate(arr, xor, initial=0))
        return [s[r + 1] ^ s[l] for l, r in queries]
