class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def check(mid):
            m, n, i, j = len(s), len(p), 0, 0
            ids = set(removable[:mid])
            while i < m and j < n:
                if i not in ids and s[i] == p[j]:
                    j += 1
                i += 1
            return j == n

        left, right = 0, len(removable)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
