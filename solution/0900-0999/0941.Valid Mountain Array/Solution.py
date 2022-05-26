class Solution:
    def validMountainArray(self, arr: List[int]) -> bool:
        n = len(arr)
        if n < 3:
            return False
        l, r = 0, n - 1
        while l + 1 < n - 1 and arr[l] < arr[l + 1]:
            l += 1
        while r - 1 > 0 and arr[r] < arr[r - 1]:
            r -= 1
        return l == r
