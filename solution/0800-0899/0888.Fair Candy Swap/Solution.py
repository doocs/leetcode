class Solution:
    def fairCandySwap(self, aliceSizes: List[int], bobSizes: List[int]) -> List[int]:
        diff = (sum(aliceSizes) - sum(bobSizes)) >> 1
        s = set(bobSizes)
        for a in aliceSizes:
            target = a - diff
            if target in s:
                return [a, target]
