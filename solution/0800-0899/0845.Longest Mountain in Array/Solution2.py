class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        n = len(arr)
        ans = l = 0
        while l + 2 < n:
            r = l + 1
            if arr[l] < arr[r]:
                while r + 1 < n and arr[r] < arr[r + 1]:
                    r += 1
                if r < n - 1 and arr[r] > arr[r + 1]:
                    while r < n - 1 and arr[r] > arr[r + 1]:
                        r += 1
                    ans = max(ans, r - l + 1)
                else:
                    r += 1
            l = r
        return ans
