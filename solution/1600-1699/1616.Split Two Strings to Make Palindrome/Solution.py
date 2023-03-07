class Solution:
    def checkPalindromeFormation(self, a: str, b: str) -> bool:
        def check1(a: str, b: str) -> bool:
            i, j = 0, len(b) - 1
            while i < j and a[i] == b[j]:
                i, j = i + 1, j - 1
            return i >= j or check2(a, i, j) or check2(b, i, j)

        def check2(a: str, i: int, j: int) -> bool:
            return a[i : j + 1] == a[i : j + 1][::-1]

        return check1(a, b) or check1(b, a)
