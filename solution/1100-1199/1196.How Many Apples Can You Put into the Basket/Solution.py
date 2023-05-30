class Solution:
    def maxNumberOfApples(self, weight: List[int]) -> int:
        weight.sort()
        s = 0
        for i, x in enumerate(weight):
            s += x
            if s > 5000:
                return i
        return len(weight)
