class Solution:
    def maximumCoins(self, coins: List[List[int]], k: int) -> int:
        coins.sort(key=itemgetter(0))
        i, total, ans, n = 0, 0, 0, len(coins)
        l0, r0, c0 = coins[0]
        print(coins)
        for l, r, c in coins:
            while i < n and r - coins[i][0] + 1 >= k:
                # print(r,coins[i][0],total)
                l0, r0, c0 = coins[i]
                ans = max(total + max(k + l0 - l, 0) * c, ans)
                total -= (r0 - l0 + 1) * c0
                i += 1

            total += (r - l + 1) * c
            # print(total,l,r)
            if i < n and coins[i][0] != l0:
                start_l = r - k + 1
                ans = max(total + max(r0 - start_l + 1, 0) * c0, ans)
        return max(ans, total)
