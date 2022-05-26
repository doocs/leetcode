class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        bucket = [0] * 101
        for h in heights:
            bucket[h] += 1
        res = i = 0
        for j in range(1, 101):
            while bucket[j] > 0:
                bucket[j] -= 1
                if heights[i] != j:
                    res += 1
                i += 1
        return res
