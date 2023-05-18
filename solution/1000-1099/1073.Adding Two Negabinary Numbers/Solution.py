class Solution:
    def addNegabinary(self, arr1: List[int], arr2: List[int]) -> List[int]:
        i, j = len(arr1) - 1, len(arr2) - 1
        c = 0
        ans = []
        while i >= 0 or j >= 0 or c:
            a = 0 if i < 0 else arr1[i]
            b = 0 if j < 0 else arr2[j]
            x = a + b + c
            c = 0
            if x >= 2:
                x -= 2
                c -= 1
            elif x == -1:
                x = 1
                c += 1
            ans.append(x)
            i, j = i - 1, j - 1
        while len(ans) > 1 and ans[-1] == 0:
            ans.pop()
        return ans[::-1]
