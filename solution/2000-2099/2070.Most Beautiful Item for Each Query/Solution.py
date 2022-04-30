class Solution:
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        items.sort()
        prices = [p for p, _ in items]
        mx = [items[0][1]]
        for _, b in items[1:]:
            mx.append(max(mx[-1], b))
        ans = [0] * len(queries)
        for i, q in enumerate(queries):
            j = bisect_right(prices, q)
            if j:
                ans[i] = mx[j - 1]
        return ans
