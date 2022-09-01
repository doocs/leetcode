class Solution:
    def canThreePartsEqualSum(self, arr: List[int]) -> bool:
        s = sum(arr)
        if s % 3 != 0:
            return False
        i, j = 0, len(arr) - 1
        a = b = 0
        while i < len(arr):
            a += arr[i]
            if a == s // 3:
                break
            i += 1
        while ~j:
            b += arr[j]
            if b == s // 3:
                break
            j -= 1
        return i < j - 1
