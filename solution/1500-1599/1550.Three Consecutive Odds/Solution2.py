class Solution:
    def threeConsecutiveOdds(self, arr: list[int]) -> bool:
        return any(x & arr[i + 1] & arr[i + 2] & 1 for i, x in enumerate(arr[:-2]))
