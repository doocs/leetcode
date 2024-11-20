class Solution:
    def maximumRemovals(self, s: str, p: str, removable: List[int]) -> int:
        def check(k: int) -> bool:
            rem = [False] * len(s)
            for i in removable[:k]:
                rem[i] = True
            i = j = 0
            while i < len(s) and j < len(p):
                if not rem[i] and p[j] == s[i]:
                    j += 1
                i += 1
            return j == len(p)

        l, r = 0, len(removable)
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
