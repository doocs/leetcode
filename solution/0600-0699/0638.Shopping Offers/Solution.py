class Solution:
    def shoppingOffers(
        self, price: List[int], special: List[List[int]], needs: List[int]
    ) -> int:
        @cache
        def dfs(cur: int) -> int:
            ans = sum(p * (cur >> (i * bits) & 0xF) for i, p in enumerate(price))
            for offer in special:
                nxt = cur
                for j in range(len(needs)):
                    if (cur >> (j * bits) & 0xF) < offer[j]:
                        break
                    nxt -= offer[j] << (j * bits)
                else:
                    ans = min(ans, offer[-1] + dfs(nxt))
            return ans

        bits, mask = 4, 0
        for i, need in enumerate(needs):
            mask |= need << i * bits
        return dfs(mask)
