class Solution:
    def maxBoxesInWarehouse(self, boxes: List[int], warehouse: List[int]) -> int:
        n = len(warehouse)
        left = [0] * n
        right = [0] * n
        left[0] = right[-1] = inf
        for i in range(1, n):
            left[i] = min(left[i - 1], warehouse[i - 1])
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], warehouse[i + 1])
        for i in range(n):
            warehouse[i] = min(warehouse[i], max(left[i], right[i]))
        boxes.sort()
        warehouse.sort()
        ans = i = 0
        for x in boxes:
            while i < n and warehouse[i] < x:
                i += 1
            if i == n:
                break
            ans, i = ans + 1, i + 1
        return ans
