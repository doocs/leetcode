class Solution:
    def maximizeSweetness(self, sweetness: List[int], k: int) -> int:
        def check(x: int) -> bool:
            s = cnt = 0
            for v in sweetness:
                s += v
                if s >= x:
                    s = 0
                    cnt += 1
            return cnt > k

        l, r = 0, sum(sweetness)
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
