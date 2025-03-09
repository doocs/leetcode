class Solution:
    def numOfUnplacedFruits(self, fruits: List[int], baskets: List[int]) -> int:
        n = len(fruits)
        vis = [False] * n
        ans = n
        for x in fruits:
            for i, y in enumerate(baskets):
                if y >= x and not vis[i]:
                    vis[i] = True
                    ans -= 1
                    break
        return ans
