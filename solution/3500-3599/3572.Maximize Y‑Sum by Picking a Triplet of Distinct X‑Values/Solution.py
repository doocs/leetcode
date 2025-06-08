class Solution:
    def maxSumDistinctTriplet(self, x: List[int], y: List[int]) -> int:
        arr = [(a, b) for a, b in zip(x, y)]
        arr.sort(key=lambda x: -x[1])
        vis = set()
        ans = 0
        for a, b in arr:
            if a in vis:
                continue
            vis.add(a)
            ans += b
            if len(vis) == 3:
                return ans
        return -1
