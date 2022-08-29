class Solution:
    def containsPattern(self, arr: List[int], m: int, k: int) -> bool:
        n = len(arr)
        for i in range(n - m * k + 1):
            j = 0
            while j < m * k:
                if arr[i + j] != arr[i + (j % m)]:
                    break
                j += 1
            if j == m * k:
                return True
        return False
