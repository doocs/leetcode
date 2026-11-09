class Solution:
    def findArray(self, pref: list[int]) -> list[int]:
        return [a ^ b for a, b in pairwise([0] + pref)]
