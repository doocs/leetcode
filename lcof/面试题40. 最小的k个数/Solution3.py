class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        def quick_sort(l, r):
            i, j = l, r
            while i < j:
                while i < j and arr[j] >= arr[l]:
                    j -= 1
                while i < j and arr[i] <= arr[l]:
                    i += 1
                arr[i], arr[j] = arr[j], arr[i]
            arr[i], arr[l] = arr[l], arr[i]
            if k < i:
                return quick_sort(l, i - 1)
            if k > i:
                return quick_sort(i + 1, r)
            return arr[:k]

        n = len(arr)
        return arr if k == n else quick_sort(0, n - 1)
