class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        boxTypes.sort(key=lambda x: -x[1])
        ans = 0
        for a, b in boxTypes:
            a = min(a, truckSize)
            truckSize -= a
            ans += a * b
            if truckSize == 0:
                break
        return ans
