class Solution:
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        pre_xor = [0] * (len(arr) + 1)
        for i in range(1, len(arr) + 1):
            pre_xor[i] = pre_xor[i - 1] ^ arr[i - 1]
        return [pre_xor[l] ^ pre_xor[r + 1] for l, r in queries]
