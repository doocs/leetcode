class Solution:
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        items.sort()
        prices = [p for p, _ in items]
        n = len(items)
        mx = [items[0][1]]
        for i in range(1, n):
            mx.append(max(mx[i - 1], items[i][1]))
        ans = []
        for q in queries:
            j = bisect_right(prices, q) - 1
            ans.append(0 if j < 0 else mx[j])
        return ans
