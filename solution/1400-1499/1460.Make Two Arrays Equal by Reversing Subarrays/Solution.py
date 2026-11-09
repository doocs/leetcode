class Solution:
    def canBeEqual(self, target: list[int], arr: list[int]) -> bool:
        return sorted(target) == sorted(arr)
