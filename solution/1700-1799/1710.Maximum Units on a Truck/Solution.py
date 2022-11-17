class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        ans = 0
        for a, b in sorted(boxTypes, key=lambda x: -x[1]):
            ans += b * min(truckSize, a)
            truckSize -= a
            if truckSize <= 0:
                break
        return ans
