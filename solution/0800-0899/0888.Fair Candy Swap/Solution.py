class Solution:
    def fairCandySwap(self, aliceSizes: list[int], bobSizes: list[int]) -> list[int]:
        diff = (sum(aliceSizes) - sum(bobSizes)) >> 1
        s = set(bobSizes)
        for a in aliceSizes:
            if (b := (a - diff)) in s:
                return [a, b]
