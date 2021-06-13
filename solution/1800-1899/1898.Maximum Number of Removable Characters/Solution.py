class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def isSubsequence(mid):
            m, n = len(s), len(p)
            i = j = 0
            ids = {e for e in removable[:mid]}
            while i < m and j < n:
                if i not in ids and s[i] == p[j]:
                    j += 1
                i += 1
            return j == n

        low, high = 0, len(removable)
        while low < high:
            mid = (low + high + 1) >> 1
            if isSubsequence(mid):
                low = mid
            else:
                high = mid - 1
        return low
