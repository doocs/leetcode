class Solution:
    def maxDistance(self, position: List[int], m: int) -> int:
        position.sort()

        def check(f):
            pre = position[0]
            cnt = 1
            for pos in position[1:]:
                if pos - pre >= f:
                    cnt += 1
                    pre = pos
            return cnt >= m

        left, right = 1, position[-1]
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
