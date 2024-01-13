class Solution:
    def search(self, arr: List[int], target: int) -> int:
        l, r = 0, len(arr) - 1
        while arr[l] == arr[r]:
            r -= 1
        while l < r:
            mid = (l + r) >> 1
            if arr[mid] > arr[r]:
                if arr[l] <= target <= arr[mid]:
                    r = mid
                else:
                    l = mid + 1
            elif arr[mid] < arr[r]:
                if arr[mid] < target <= arr[r]:
                    l = mid + 1
                else:
                    r = mid
            else:
                r -= 1
        return l if arr[l] == target else -1
