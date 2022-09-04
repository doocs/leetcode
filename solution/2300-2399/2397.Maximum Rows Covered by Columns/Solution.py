class Solution:
    def maximumRows(self, mat: List[List[int]], cols: int) -> int:
        arr = []
        for i, row in enumerate(mat):
            x = 0
            for j, v in enumerate(row):
                x |= v << j
            arr.append(x)
        ans, n = 0, len(mat[0])
        for mask in range(1, 1 << n | 1):
            if mask.bit_count() > cols:
                continue
            t = sum((v & mask) == v for v in arr)
            ans = max(ans, t)
        return ans
