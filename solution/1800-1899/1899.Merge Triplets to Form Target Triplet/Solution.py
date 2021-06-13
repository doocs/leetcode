class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        maxA = maxB = maxC = 0
        tA, tB, tC = target
        for a, b, c in triplets:
            if a <= tA and b <= tB and c <= tC:
                maxA = max(maxA, a)
                maxB = max(maxB, b)
                maxC = max(maxC, c)
        return (maxA, maxB, maxC) == (tA, tB, tC)
