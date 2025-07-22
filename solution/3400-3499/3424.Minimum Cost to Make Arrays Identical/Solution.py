class Solution:
    def minCost(self, arr: List[int], brr: List[int], k: int) -> int:
        c1 = sum(abs(a - b) for a, b in zip(arr, brr))
        arr.sort()
        brr.sort()
        c2 = k + sum(abs(a - b) for a, b in zip(arr, brr))
        return min(c1, c2)
