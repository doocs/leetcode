class Solution:
    def findLongestWord(self, s: str, dictionary: List[str]) -> str:
        def check(a, b):
            m, n = len(a), len(b)
            i = j = 0
            while i < m and j < n:
                if a[i] == b[j]:
                    j += 1
                i += 1
            return j == n

        ans = ''
        for a in dictionary:
            if check(s, a) and (len(ans) < len(a) or (len(ans) == len(a) and ans > a)):
                ans = a
        return ans
