class Solution:
    def longestCommonPrefix(self, arr1: List[int], arr2: List[int]) -> int:
        s = set()
        for x in arr1:
            while x:
                s.add(x)
                x //= 10
        mx = 0
        for x in arr2:
            while x:
                if x in s:
                    mx = max(mx, x)
                    break
                x //= 10
        return len(str(mx)) if mx else 0
