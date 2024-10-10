class Solution:
    def replaceElements(self, arr: List[int]) -> List[int]:
        mx = -1
        for i in reversed(range(len(arr))):
            x = arr[i]
            arr[i] = mx
            mx = max(mx, x)
        return arr
