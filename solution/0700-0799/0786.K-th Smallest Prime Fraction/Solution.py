class Solution:
    def kthSmallestPrimeFraction(self, arr: List[int], k: int) -> List[int]:
        h = [(1 / y, 0, j + 1) for j, y in enumerate(arr[1:])]
        heapify(h)
        for _ in range(k - 1):
            _, i, j = heappop(h)
            if i + 1 < j:
                heappush(h, (arr[i + 1] / arr[j], i + 1, j))
        return [arr[h[0][1]], arr[h[0][2]]]
