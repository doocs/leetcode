class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        def check(x):
            cnt = 1
            s = price[0]
            for p in price[1:]:
                if p - s >= x:
                    s = p
                    cnt += 1
            return cnt >= k

        price.sort()
        left, right = 0, 10**9
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
