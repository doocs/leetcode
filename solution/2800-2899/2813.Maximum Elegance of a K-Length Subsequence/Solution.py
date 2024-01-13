class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        items.sort(key=lambda x: -x[0])
        tot = 0
        vis = set()
        dup = []
        for p, c in items[:k]:
            tot += p
            if c not in vis:
                vis.add(c)
            else:
                dup.append(p)
        ans = tot + len(vis) ** 2
        for p, c in items[k:]:
            if c in vis or not dup:
                continue
            vis.add(c)
            tot += p - dup.pop()
            ans = max(ans, tot + len(vis) ** 2)
        return ans
