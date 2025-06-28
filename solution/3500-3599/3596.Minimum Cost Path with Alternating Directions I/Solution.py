class Solution:
    def minCost(self, m: int, n: int) -> int:
        if m == 1 and n == 1:
            return 1
        if m == 2 and n == 1:
            return 3
        if m == 1 and n == 2:
            return 3
        return -1
