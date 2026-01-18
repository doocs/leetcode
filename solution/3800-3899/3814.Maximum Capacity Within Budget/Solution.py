class Solution:
    def maxCapacity(self, costs: List[int], capacity: List[int], budget: int) -> int:
        arr = []
        for a, b in zip(costs, capacity):
            if a < budget:
                arr.append((a, b))
        if not arr:
            return 0
        arr.sort()
        remain = SortedList()
        for i, (_, b) in enumerate(arr):
            remain.add((b, i))
        i, j = 0, len(arr) - 1
        ans = remain[-1][0]
        while i < j:
            remain.discard((arr[i][1], i))
            while i < j and arr[i][0] + arr[j][0] >= budget:
                remain.discard((arr[j][1], j))
                j -= 1
            if remain:
                ans = max(ans, arr[i][1] + remain[-1][0])
            i += 1
        return ans
