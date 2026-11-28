class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        freq = [0] * (n + 1)
        min_price = float('inf')

        for factor, price in items:
            freq[factor] += 1
            if price < min_price:
                min_price = price

        div_count = [0] * (n + 1)
        for f in range(1, n + 1):
            if freq[f]:
                for m in range(f, n + 1, f):
                    div_count[f] += freq[m]

        sorted_items = sorted((price, div_count[factor] - 1) for factor, price in items)

        ans = 0
        rem = budget
        limit = 2 * min_price

        for price, gain in sorted_items:
            if price >= limit:
                break
            if gain <= 0:
                continue

            can_buy = min(gain, rem // price)
            ans += can_buy * 2
            rem -= can_buy * price

            if can_buy < gain:
                break

        ans += rem // min_price
        return ans
