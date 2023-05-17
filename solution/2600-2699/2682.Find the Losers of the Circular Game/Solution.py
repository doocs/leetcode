class Solution:
    def circularGameLosers(self, n: int, k: int) -> List[int]:
        vis = [False] * n
        i, p = 0, 1
        while not vis[i]:
            vis[i] = True
            i = (i + p * k) % n
            p += 1
        return [i + 1 for i in range(n) if not vis[i]]
