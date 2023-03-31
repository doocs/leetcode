class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        x, y, z = target
        d = e = f = 0
        for a, b, c in triplets:
            if a <= x and b <= y and c <= z:
                d = max(d, a)
                e = max(e, b)
                f = max(f, c)
        return [d, e, f] == target
