class Solution:
    def maxDistance(self, position: List[int], m: int) -> int:
        def check(f):
            prev = position[0]
            cnt = 1
            for curr in position[1:]:
                if curr - prev >= f:
                    prev = curr
                    cnt += 1
            return cnt >= m

        position.sort()
        left, right = 1, position[-1]
        while left < right:
            mid = (left + right + 1) >> 1

            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
