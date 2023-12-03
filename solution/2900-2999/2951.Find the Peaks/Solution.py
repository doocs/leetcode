class Solution:
    def findPeaks(self, mountain: List[int]) -> List[int]:
        return [
            i
            for i in range(1, len(mountain) - 1)
            if mountain[i - 1] < mountain[i] > mountain[i + 1]
        ]
