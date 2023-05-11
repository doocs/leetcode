class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        s = list(accumulate(arr, xor, initial=0))
        return [s[r + 1] ^ s[l] for l, r in queries]
