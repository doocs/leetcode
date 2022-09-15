class Solution:
    def maxProfit(self, inventory: List[int], orders: int) -> int:
        inventory.sort(reverse=True)
        mod = 10**9 + 7
        ans = i = 0
        n = len(inventory)
        while orders > 0:
            while i < n and inventory[i] >= inventory[0]:
                i += 1
            nxt = 0
            if i < n:
                nxt = inventory[i]
            cnt = i
            x = inventory[0] - nxt
            tot = cnt * x
            if tot > orders:
                decr = orders // cnt
                a1, an = inventory[0] - decr + 1, inventory[0]
                ans += (a1 + an) * decr // 2 * cnt
                ans += (inventory[0] - decr) * (orders % cnt)
            else:
                a1, an = nxt + 1, inventory[0]
                ans += (a1 + an) * x // 2 * cnt
                inventory[0] = nxt
            orders -= tot
            ans %= mod
        return ans
