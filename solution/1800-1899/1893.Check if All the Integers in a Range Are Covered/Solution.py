class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:
        diff = [0 for i in range(52)]
        for rg in ranges:
            diff[rg[0]] += 1
            diff[rg[1] + 1] -= 1
        cur = 0
        for i, df in enumerate(diff):
            cur += df
            if left <= i <= right and cur == 0:
                return False
        return True
