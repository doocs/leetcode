class Solution:
    def canMakeArithmeticProgression(self, arr: list[int]) -> bool:
        arr.sort()
        d = arr[1] - arr[0]
        return all(b - a == d for a, b in pairwise(arr))
