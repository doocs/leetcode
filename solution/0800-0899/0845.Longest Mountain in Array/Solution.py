class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        left, right = 0, 1
        status = -1
        ans = 0
        while right < len(arr):
            if status == -1 or status == 1:
                if arr[right] == arr[right - 1]:
                    status = -1
                if status == -1:
                    if arr[right] > arr[right - 1]:
                        status = 1
                    else:
                        left = right
                if status == 1 and arr[right] < arr[right - 1]:
                    status = 2
            else:
                if arr[right] == arr[right - 1]:
                    status = -1
                    ans = max(ans, right - left)
                    left = right
                elif arr[right] > arr[right - 1]:
                    status = 1
                    ans = max(ans, right - left)
                    left = right - 1
            right += 1
        if status == 2:
            ans = max(right - left, ans)
        return ans
